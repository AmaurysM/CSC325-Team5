package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.controllers.loginViewControllers.LoginController;
import com.csc325Team5.payrollapplication.model.UserManager;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {

    private Stage primaryStage;

    private ScreenController managerScreenController;

    @FXML
    private MFXButton EmployeesButton;

    @FXML
    private Label IDLabel;

    @FXML
    private MFXButton LogoutButton;

    @FXML
    private Label NameLabel;

    @FXML
    private MFXButton NotesButton;

    @FXML
    private MFXButton SettingsButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane centerAnchorPane;

    @FXML
    private MFXButton sendPayRollButton;

    @FXML
    private StackPane stackPane;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ScreenController getManagerScreenController() {
        return managerScreenController;
    }

    public void setManagerScreenController(ScreenController managerScreenController) {
        this.managerScreenController = managerScreenController;
    }

    public MFXButton getEmployeesButton() {
        return EmployeesButton;
    }

    public void setEmployeesButton(MFXButton employeesButton) {
        EmployeesButton = employeesButton;
    }

    public Label getIDLabel() {
        return IDLabel;
    }

    public void setIDLabel(Label IDLabel) {
        this.IDLabel = IDLabel;
    }

    public MFXButton getLogoutButton() {
        return LogoutButton;
    }

    public void setLogoutButton(MFXButton logoutButton) {
        LogoutButton = logoutButton;
    }

    public Label getNameLabel() {
        return NameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        NameLabel = nameLabel;
    }

    public MFXButton getNotesButton() {
        return NotesButton;
    }

    public void setNotesButton(MFXButton notesButton) {
        NotesButton = notesButton;
    }

    public MFXButton getSettingsButton() {
        return SettingsButton;
    }

    public void setSettingsButton(MFXButton settingsButton) {
        SettingsButton = settingsButton;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public AnchorPane getCenterAnchorPane() {
        return centerAnchorPane;
    }

    public void setCenterAnchorPane(AnchorPane centerAnchorPane) {
        this.centerAnchorPane = centerAnchorPane;
    }

    public MFXButton getSendPayRollButton() {
        return sendPayRollButton;
    }

    public void setSendPayRollButton(MFXButton sendPayRollButton) {
        this.sendPayRollButton = sendPayRollButton;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
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
        ScreenController.setMap( new HashMap<String, FXMLLoader>());
        FXMLLoader loader = new FXMLLoader(App.class.getResource("loginView/login-View.fxml"));
        loader.load();
        ((LoginController)loader.getController()).setPrimaryStage(primaryStage);
        ScreenController.addScreen("loginScreen", loader);
        ScreenController.activate("loginScreen");
        primaryStage.setWidth(620);
        primaryStage.setHeight(400);

    }


    public void refreshTableView(){
        EmployeesTabController controller = ((EmployeesTabController)ScreenController.findController("employeesTab"));
        controller.refreshTableView();

    }


    @FXML
    void sendPayRoll(ActionEvent event) {
        UserManager.getUserBag().forEach( e ->{
            e.getPayStubManager().addPayStub(e);
            }
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ScreenController.setTitleBarText("PAYROLL - Manager");
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("managerView/employeesTab.fxml"));
            loader.load();
            ScreenController.addScreen("employeesTab", loader);
            ((EmployeesTabController)loader.getController()).setPrimaryStage(primaryStage);

            loader = new FXMLLoader(App.class.getResource("managerView/notesTab.fxml"));
            loader.load();
            ScreenController.addScreen("notesTab", loader);

            loader = new FXMLLoader(App.class.getResource("managerView/payrollTab.fxml"));
            loader.load();
            ScreenController.addScreen("payrollTab", loader);

            loader = new FXMLLoader(App.class.getResource("managerView/settingsTab.fxml"));
            loader.load();
            ScreenController.addScreen("settingsTab", loader);

            loader = new FXMLLoader(App.class.getResource("managerView/createNote-View.fxml"));
            loader.load();
            ScreenController.addScreen("createNote", loader);

            loader = new FXMLLoader(App.class.getResource("managerView/createOrEditUser-View.fxml"));
            loader.load();
            ScreenController.addScreen("createOrEditUser", loader);

            borderPane.setCenter(ScreenController.find("employeesTab"));
            
            NameLabel.setText(UserManager.getCurrentUser().getName());
            IDLabel.setText(UserManager.getCurrentUser().getID());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
