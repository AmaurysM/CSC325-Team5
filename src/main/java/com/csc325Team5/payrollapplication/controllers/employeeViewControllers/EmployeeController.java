package com.csc325Team5.payrollapplication.controllers.employeeViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.User;
import com.csc325Team5.payrollapplication.model.UserBag;
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
        ScreenController.setMap( new HashMap<String, Object[]>());

        FXMLLoader loader = new FXMLLoader(App.class.getResource("login-View.fxml"));
        ScreenController.addScreen("loginScreen", loader.load(),loader.getController());

        ScreenController.activate("loginScreen");
    }

    @FXML
    void clockIn(ActionEvent event) {
        User user = UserBag.getCurrentUser();
        if(!user.isClockedIn()) {
            user.setClockInTime(UserBag.getCurrentUser().getCurrentTime());
            user.setClockedIn(true);
        }
    }

    @FXML
    void clockOut(ActionEvent event) throws ParseException {
        User user = UserBag.getCurrentUser();
        if(user.isClockedIn()) {
            user.setClockOutTime(UserBag.getCurrentUser().getCurrentTime());
            user.setClockedIn(false);

            System.out.println(user.getTimeDifference(user.getClockInTime(),user.getClockOutTime()));

        }

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

