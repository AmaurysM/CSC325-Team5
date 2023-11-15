package com.csc325Team5.payrollapplication.controllers.employeeViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.User;
import com.csc325Team5.payrollapplication.model.UserManager;
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
import java.text.ParseException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

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
        ScreenController.setMap( new HashMap<String, FXMLLoader>());
        FXMLLoader loader = new FXMLLoader(App.class.getResource("loginView/login-View.fxml"));
        loader.load();
        ScreenController.addScreen("loginScreen", loader);
        ScreenController.activate("loginScreen");
    }

    @FXML
    void clockIn(ActionEvent event) {
        User user = UserManager.getCurrentUser();
        if(!user.isClockedIn()) {
            user.setClockInTime(UserManager.getCurrentUser().getCurrentTime());
            user.setClockedIn(true);
        }
    }

    @FXML
    void clockOut(ActionEvent event) throws ParseException {
        User user = UserManager.getCurrentUser();
        if(user.isClockedIn()) {
            user.setClockOutTime(UserManager.getCurrentUser().getCurrentTime());
            user.setClockedIn(false);
            double hoursWorked = user.getTimeDifference(user.getClockInTime(),user.getClockOutTime());
            user.setHoursWorkedThisWeek(user.getHoursWorkedThisWeek() + hoursWorked);

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Image image = new Image(String.valueOf(App.class.getResource("images/profile-pic.png")));
            profilePictureCircle.setFill(new ImagePattern(image));

            FXMLLoader loader = new FXMLLoader(App.class.getResource("employeeView/payrollTab.fxml"));
            loader.load();
            ScreenController.addScreen("payrollTab", loader);

            loader = new FXMLLoader(App.class.getResource("employeeView/notesTab.fxml"));
            loader.load();
            ScreenController.addScreen("notesTab", loader);

            loader = new FXMLLoader(App.class.getResource("employeeView/settingsTab.fxml"));
            loader.load();
            ScreenController.addScreen("settingsTab", loader);

            borderPane.setCenter(ScreenController.find("payrollTab"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

