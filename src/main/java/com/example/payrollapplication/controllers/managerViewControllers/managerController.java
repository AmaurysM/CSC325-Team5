package com.example.payrollapplication.controllers.managerViewControllers;

import com.example.payrollapplication.App;
import com.example.payrollapplication.controllers.ScreenController;
import com.example.payrollapplication.model.User;
import com.example.payrollapplication.model.UserBag;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class managerController implements Initializable {

    @FXML
    private MFXButton EmployeesButton;

    @FXML
    private MFXButton LogoutButton;

    @FXML
    private MFXButton NotesButton;

    @FXML
    private MFXButton PayrollButton;

    @FXML
    private MFXButton SettingsButton;



    @FXML
    private Pane centerPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    void logoutOfManagerView(ActionEvent event) {
        ScreenController.activate("loginScreen");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FXMLLoader employeesTabLoader = new FXMLLoader(App.class.getResource("employeesTab.fxml"));

        try {

            employeesTabLoader.load();
            //borderPane.setCenter(employeesTabLoader.getRoot());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
