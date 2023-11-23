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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    private Stage primaryStage;

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
    private AnchorPane anchorPane;

    @FXML
    private ScrollPane scrollPane;

    public MFXButton getLogoutButton() {
        return LogoutButton;
    }

    public void setLogoutButton(MFXButton logoutButton) {
        LogoutButton = logoutButton;
    }

    public MFXButton getNotesButton() {
        return NotesButton;
    }

    public void setNotesButton(MFXButton notesButton) {
        NotesButton = notesButton;
    }

    public MFXButton getPayrollButton() {
        return PayrollButton;
    }

    public void setPayrollButton(MFXButton payrollButton) {
        PayrollButton = payrollButton;
    }

    public MFXButton getSettingsButton() {
        return SettingsButton;
    }

    public void setSettingsButton(MFXButton settingsButton) {
        SettingsButton = settingsButton;
    }

    public Circle getProfilePictureCircle() {
        return profilePictureCircle;
    }

    public void setProfilePictureCircle(Circle profilePictureCircle) {
        this.profilePictureCircle = profilePictureCircle;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
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

    public void resizeView(){
        anchorPane.setPrefSize(scrollPane.getWidth(),scrollPane.getHeight());
    }

    @FXML
    void logoutOfEmployeeView(ActionEvent event) throws IOException {
        ScreenController.setMap( new HashMap<String, FXMLLoader>());
        FXMLLoader loader = new FXMLLoader(App.class.getResource("loginView/login-View.fxml"));
        loader.load();
        ScreenController.addScreen("loginScreen", loader);
        ScreenController.activate("loginScreen");
        primaryStage.setWidth(620);
        primaryStage.setHeight(400);
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

