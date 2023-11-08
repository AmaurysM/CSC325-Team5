package com.example.payrollapplication.controllers.managerViewControllers;

import com.example.payrollapplication.controllers.ScreenController;
import com.example.payrollapplication.model.UserBag;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class settingsTabController implements Initializable {

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

        UserBag.getCurrentUser().setName(nameTextField.getText());
        UserBag.getCurrentUser().setPassword(passwordTextField.getText());
        UserBag.getCurrentUser().setUsername(usernameTextField.getText());

        ((employeesTabController)ScreenController.findController("employeesTab")).refreshTableView();
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
        nameTextField.setText(UserBag.getCurrentUser().getName());
        usernameTextField.setText(UserBag.getCurrentUser().getUsername());
        passwordTextField.setText(UserBag.getCurrentUser().getPassword());
        idLabel.setText(UserBag.getCurrentUser().getID());
    }
}
