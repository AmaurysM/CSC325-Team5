package com.example.payrollapplication.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class employeeController {

    @FXML
    private MFXButton LogoutButton;

    @FXML
    private MFXButton NotesButton;

    @FXML
    private MFXButton PayrollButton;

    @FXML
    private MFXButton SettingsButton;

    @FXML
    private Circle profilePictureCircle;

    @FXML
    void logoutOfEmployeeView(ActionEvent event) throws IOException {
        ScreenController.activate("loginScreen");
    }
}

