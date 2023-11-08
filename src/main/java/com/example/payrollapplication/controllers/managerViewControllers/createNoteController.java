package com.example.payrollapplication.controllers.managerViewControllers;

import com.example.payrollapplication.controllers.ScreenController;
import com.example.payrollapplication.model.Note;
import com.example.payrollapplication.model.User;
import com.example.payrollapplication.model.UserBag;
import com.jfoenix.controls.JFXComboBox;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class createNoteController implements Initializable {

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
        createNoteController noteController = (createNoteController) ScreenController.findController("createNote");
        managerController manager = (managerController) ScreenController.findController("manager");
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
        notesTabController controller = (notesTabController)ScreenController.findController("notesTab");
        controller.refreshTableView();
    }

    @FXML
    void sendNewNote(ActionEvent event) {
        if(!allFieldsFilled()){
            return;
        }

        User user = employeeComboBox.getSelectionModel().getSelectedItem();

        user.addNote(UserBag.getCurrentUser(), noteTextArea.getText(), user);
        UserBag.getCurrentUser().addNote(user.getNotes().get(user.getNotes().size()-1));
        refreshTableView();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserBag.getUserBag().stream().filter(e -> e.getRole().compareTo("manager") != 0)
                .forEach(e-> employeeComboBox.getItems().add(e));
    }
}
