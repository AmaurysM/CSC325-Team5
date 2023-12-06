package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.UserManager;
import com.google.cloud.firestore.DocumentReference;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsTabController implements Initializable {

    @FXML
    private MFXButton editButton;

    @FXML
    private Label idLabel;

    @FXML
    private MFXButton logoutButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    void editCurrentUser(ActionEvent event) {
        if(!allFieldsFilled()){
            return;
        }

        UserManager.getCurrentUser().setName(nameTextField.getText());
        UserManager.getCurrentUser().setPassword(passwordTextField.getText());
        UserManager.getCurrentUser().setUsername(usernameTextField.getText());
        DocumentReference docRef = App.fstore.collection("Users").document(UserManager.getCurrentUser().getUsername());
        docRef.update("User_Name",usernameTextField.getText());
        docRef.update("Name", nameTextField.getText());
        docRef.update("Password",passwordTextField.getText());
        ((EmployeesTabController)ScreenController.findController("employeesTab")).refreshTableView();
    }

    public boolean allFieldsFilled(){
        if(nameTextField.getText().isBlank()){
            return false;
        }

        if(usernameTextField.getText().isBlank()){
            return false;
        }

        if(passwordTextField.getText().isBlank()){
            return false;
        }

        return true;

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        ScreenController.activate("loginScreen");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameTextField.setText(UserManager.getCurrentUser().getName());
        usernameTextField.setText(UserManager.getCurrentUser().getUsername());
        passwordTextField.setText(UserManager.getCurrentUser().getPassword());
        idLabel.setText(UserManager.getCurrentUser().getID());
    }
}
