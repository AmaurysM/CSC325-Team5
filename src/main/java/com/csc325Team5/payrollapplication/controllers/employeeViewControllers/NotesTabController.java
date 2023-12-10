package com.csc325Team5.payrollapplication.controllers.employeeViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.model.Note;
import com.csc325Team5.payrollapplication.model.NoteManager;
import com.csc325Team5.payrollapplication.model.User;
import com.csc325Team5.payrollapplication.model.UserManager;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.jfoenix.controls.JFXComboBox;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class NotesTabController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private MFXButton searchButton;

    @FXML
    private MFXTextField textField;

    @FXML
    private JFXComboBox<String> filterComboBox;

    @FXML
    private TilePane tilePane;

    @FXML
    void search(ActionEvent event) {
        if(filterComboBox.getSelectionModel().isEmpty()){
           return;
        }

        switch (filterComboBox.getSelectionModel().getSelectedItem()){
            case "RECENT":
                sortTilePane(Comparator.comparing(Note::getTime).reversed());
                break;

            case "OLDEST":
                sortTilePane(Comparator.comparing(Note::getTime));
                break;

            case "NAME A-Z":
                sortTilePane(Comparator.comparing(note -> note.getSender().getName()));
                break;

            case "NAME Z-A":
                sortTilePane(Comparator.<Note, String>comparing(note -> note.getSender().getName()).reversed());
                break;

            default:
                //
        }


    }

    public void sortTilePane(Comparator<Note> compare){
        tilePane.getChildren().clear();
        PriorityQueue<Note> priorityQueue = new PriorityQueue<>(compare);
        priorityQueue.addAll(UserManager.getCurrentUser().getNoteManager().getNotes());
        while(!priorityQueue.isEmpty()){
            tilePane.getChildren().add(newNote(priorityQueue.poll()));
        }

    }

    public void filterNotes(Predicate<Note> predicate){
        UserManager.getCurrentUser().getNoteManager().getNotes().stream().filter(predicate).forEach((e)->{
            tilePane.getChildren().add(newNote(e));
        });
    }

    public Node newNote(Note note){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("employeeView/notesWidget.fxml"));
        try {
            loader.load();
            NotesWidgetController controller = loader.getController();
            controller.createWidget(note,loader.getController());
            return controller.getAnchorPane();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] filters = {"RECENT","OLDEST","NAME A-Z", "NAME Z-A"};
        filterComboBox.getItems().addAll(filters);

        ApiFuture<QuerySnapshot> future =  App.fstore.collection("Users").get();
        List<QueryDocumentSnapshot> documents;
        int i = 0;
        try
        {
            documents = future.get().getDocuments();
            if(documents.size()>0)
            {

                for (QueryDocumentSnapshot document : documents)
                {
                    NoteManager nm = new NoteManager();
                    User reciever = UserManager.findUserByUserName((String) document.getData().get("User_Name"));
                    System.out.println("The reciever id is " +(String) document.getData().get("ID") );

                    ArrayList notesList = (ArrayList) document.getData().get("Notes");
                    ArrayList senderList = (ArrayList) document.getData().get("SenderList");


                    if(senderList != null){

                        for(i = 0; i < senderList.size();i++){
                            User sender = UserManager.findUserByUserName((String) senderList.get(i));
                            nm.addNote(sender, (String) notesList.get(i),reciever);
                        }
//                        senderList.forEach(sender->{
//
//                            User sender1 = UserManager.findUserByUserName((String) senderList.get(i));
//                            System.out.println("The found sender is ");
//                            System.out.println(sender1.getUsername());
//
//                        });
                       reciever.setNoteManager(nm);
                    }





//                    if(notesList != null){
//                        notesList.forEach(note->{
//                            User sender = UserManager.findUserByUserName((String) senderList.get(i));
//                            nm.addNote(sender, (String) note,reciever);
//                        });
//                       reciever.setNoteManager(nm);
//                    }

                }
            }

        }
        catch (InterruptedException | ExecutionException ex)
        {
            ex.printStackTrace();
        }


        UserManager.getUserBag().forEach(e->{
            if(UserManager.getCurrentUser().getName().equals(e.getName())){
                e.getNoteManager().getNotes().forEach(b->{
                    tilePane.getChildren().add(newNote(b));

                });
            }
        });

        filterComboBox.getSelectionModel().select(0);
    }
}

