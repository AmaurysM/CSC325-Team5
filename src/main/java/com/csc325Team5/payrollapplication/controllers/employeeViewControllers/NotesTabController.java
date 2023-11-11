package com.csc325Team5.payrollapplication.controllers.employeeViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.model.Note;
import com.csc325Team5.payrollapplication.model.UserBag;
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
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;
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
        priorityQueue.addAll(UserBag.getCurrentUser().getNotes());
        while(!priorityQueue.isEmpty()){
            tilePane.getChildren().add(newNote(priorityQueue.poll()));
        }

    }

    public void filterNotes(Predicate<Note> predicate){
        UserBag.getCurrentUser().getNotes().stream().filter(predicate).forEach((e)->{
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

        UserBag.getCurrentUser().getNotes().stream().sorted(Comparator.comparing(Note::getTime).reversed()).forEach((e)->{
            tilePane.getChildren().add(newNote(e));
        });
    }
}

