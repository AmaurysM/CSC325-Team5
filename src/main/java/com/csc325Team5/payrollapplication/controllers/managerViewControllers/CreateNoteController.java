package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.UserManager;
import com.csc325Team5.payrollapplication.model.User;
import com.csc325Team5.payrollapplication.utilities.Role;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.jfoenix.controls.JFXComboBox;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class CreateNoteController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXComboBox<User> employeeComboBox;

    @FXML
    private TextArea noteTextArea;

    @FXML
    private MFXButton quitButton;

    @FXML
    private MFXButton sendButton;

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public JFXComboBox<User> getEmployeeComboBox() {
        return employeeComboBox;
    }

    public void setEmployeeComboBox(JFXComboBox<User> employeeComboBox) {
        this.employeeComboBox = employeeComboBox;
    }

    public TextArea getNoteTextArea() {
        return noteTextArea;
    }

    public void setNoteTextArea(TextArea noteTextArea) {
        this.noteTextArea = noteTextArea;
    }

    public MFXButton getQuitButton() {
        return quitButton;
    }

    public void setQuitButton(MFXButton quitButton) {
        this.quitButton = quitButton;
    }

    public MFXButton getSendButton() {
        return sendButton;
    }

    public void setSendButton(MFXButton sendButton) {
        this.sendButton = sendButton;
    }

    @FXML
    void quitNote(ActionEvent event) {
        CreateNoteController noteController = (CreateNoteController) ScreenController.findController("createNote");
        ManagerController manager = (ManagerController) ScreenController.findController("manager");
        manager.getStackPane().getChildren().remove(noteController.getAnchorPane());
    }

    public boolean allFieldsFilled(){
        if(employeeComboBox.getSelectionModel().isEmpty()){
            return false;
        }

        if(noteTextArea.getText().isBlank()){
            return false;
        }

        return true;
    }

    private void refreshTableView(){
        NotesTabController controller = (NotesTabController)ScreenController.findController("notesTab");
        controller.refreshTableView();
    }

    private static class Note {
        private String sender;
        private String reciever;
        private String note;

        public Note(String sender, String reciever, String note){
            this.sender = sender;
            this.reciever = reciever;
            this.note = note;
        }
    }

    ArrayList notesList = new ArrayList();
    ArrayList senderList = new ArrayList();

    @FXML
    void sendNewNote(ActionEvent event) throws ExecutionException, InterruptedException {
        if(!allFieldsFilled()){
            return;
        }

        User user = employeeComboBox.getSelectionModel().getSelectedItem();
        DocumentReference docRef = App.fstore.collection("Users").document(user.getUsername());
//        new Note(UserManager.getCurrentUser().getName(),noteTextArea.getText(),user.getName())
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();
        notesList = (ArrayList) document.getData().get("Notes");
        senderList = (ArrayList) document.getData().get("SenderList");

        notesList.add(noteTextArea.getText());
        senderList.add(UserManager.getCurrentUser().getUsername());

        docRef.update("Notes",notesList);
        docRef.update("SenderList",senderList);



        user.getNoteManager().addNote(UserManager.getCurrentUser(), noteTextArea.getText(), user);
        UserManager.getCurrentUser().getNoteManager().addNote(user.getNoteManager().getNotes().get(user.getNoteManager().getNotes().size()-1));
        refreshTableView();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserManager.getUserBag().stream().filter(e -> Role.MANAGER.name().compareTo(e.getRole().toUpperCase()) != 0)
                .forEach(e-> employeeComboBox.getItems().add(e));
    }
}
