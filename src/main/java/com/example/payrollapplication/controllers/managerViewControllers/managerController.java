package com.example.payrollapplication.controllers.managerViewControllers;

import com.example.payrollapplication.App;
import javafx.application.Platform;

import com.example.payrollapplication.controllers.ScreenController;
import com.example.payrollapplication.model.User;
import com.example.payrollapplication.model.UserBag;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class managerController implements Initializable {

    private Stage primaryStage;

    private ScreenController managerScreenController;

    private Scene mainScreenController;

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
    private AnchorPane centerAnchorPane;

    public Stage getPrimaryStage() {

        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    void goToEmployeesTab(ActionEvent event) throws IOException {
        borderPane.setCenter(ScreenController.find("employeesTab"));
    }

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
    void logoutOfManagerView(ActionEvent event) throws IOException {
        ScreenController.activate("loginScreen");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("managerView/employeesTab.fxml"));
            ScreenController.addScreen("employeesTab", loader.load(),loader.getController());
            ((employeesTabController)loader.getController()).setPrimaryStage(primaryStage);

            loader = new FXMLLoader(App.class.getResource("managerView/notesTab.fxml"));
            ScreenController.addScreen("notesTab", loader.load(),loader.getController());

            loader = new FXMLLoader(App.class.getResource("managerView/payrollTab.fxml"));
            ScreenController.addScreen("payrollTab", loader.load(),loader.getController());

            loader = new FXMLLoader(App.class.getResource("managerView/settingsTab.fxml"));
            ScreenController.addScreen("settingsTab", loader.load(),loader.getController());


            borderPane.setCenter(ScreenController.find("employeesTab"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
