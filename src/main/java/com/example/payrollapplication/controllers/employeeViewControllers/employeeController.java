package com.example.payrollapplication.controllers.employeeViewControllers;

import com.example.payrollapplication.App;
import com.example.payrollapplication.controllers.ScreenController;
import com.example.payrollapplication.controllers.managerViewControllers.employeesTabController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class employeeController implements Initializable {

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
    private BorderPane borderPane;

    @FXML
    void goToNotesTab(ActionEvent event) throws IOException {
        borderPane.setCenter(ScreenController.find("notesTab"));
    }

    @FXML
    void goToPayrollTab(ActionEvent event) throws IOException {
        borderPane.setCenter(ScreenController.find("payrollTab"));
    }

    @FXML
    void goToSettingsTab(ActionEvent event) throws IOException {
        borderPane.setCenter(ScreenController.find("settingsTab"));
    }

    @FXML
    void logoutOfEmployeeView(ActionEvent event) throws IOException {
        ScreenController.removeScreen("payrollTab");
        ScreenController.removeScreen("notesTab");
        ScreenController.removeScreen("settingsTab");
        ScreenController.activate("loginScreen");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Image image = new Image(String.valueOf(App.class.getResource("images/profile-pic.png")));
            profilePictureCircle.setFill(new ImagePattern(image));

            FXMLLoader loader = new FXMLLoader(App.class.getResource("employeeView/payrollTab.fxml"));
            ScreenController.addScreen("payrollTab", loader.load(),loader.getController());

            loader = new FXMLLoader(App.class.getResource("employeeView/notesTab.fxml"));
            ScreenController.addScreen("notesTab", loader.load(),loader.getController());

            loader = new FXMLLoader(App.class.getResource("employeeView/settingsTab.fxml"));
            ScreenController.addScreen("settingsTab", loader.load(),loader.getController());

            borderPane.setCenter(ScreenController.find("payrollTab"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

