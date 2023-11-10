package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.UserBag;
import com.csc325Team5.payrollapplication.model.User;
import com.csc325Team5.payrollapplication.utilities.Role;
import com.jfoenix.controls.JFXComboBox;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

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
        UserBag.getUserBag().stream().filter(e -> Role.MANAGER.name().compareTo(e.getRole().toUpperCase()) != 0)
                .forEach(e-> employeeComboBox.getItems().add(e));
    }
}
