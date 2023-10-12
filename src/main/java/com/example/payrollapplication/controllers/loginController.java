package com.example.payrollapplication.controllers;

import com.example.payrollapplication.Util.User;
import com.example.payrollapplication.Util.UserBag;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.List;

public class loginController {

    @FXML
    private MFXButton LoginButton;

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private MFXTextField usernameTextField;

    @FXML // This tries to find the user based on username and password.
    void validateUserNameAndPassword(ActionEvent event) {
        User foundUser = UserBag.findUser(new User("0",usernameTextField.getText(),passwordField.getText(),0,"0",0,"0"));
        System.out.println(foundUser);
        if(foundUser != null ){
            UserBag.setCurrentUser(foundUser);
        }
    }
}
