package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.ScreenController;
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
    private TextField CurrentNameTextField;

    @FXML
    private TextField CurrentSalaryTextField;

    @FXML
    private TextField CurrentUserNameTextField;

    @FXML
    private MFXButton LogoutButton;

    @FXML
    private MFXButton NotesButton;

    @FXML
    private MFXButton PayrollButton;

    @FXML
    private MFXButton SettingsButton;


    @FXML
    private StackPane addUserStackPane;

    @FXML
    private TextField ageTextField;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane centerAnchorPane;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private MFXButton sendPayRollButton;
    @FXML
    private TextField roleTextField;

    @FXML
    private TextField SalaryTextField;

    @FXML
    private StackPane stackPane;

    public ScreenController getManagerScreenController() {
        return managerScreenController;
    }

    public void setManagerScreenController(ScreenController managerScreenController) {
        this.managerScreenController = managerScreenController;
    }

    public TextField getCurrentNameTextField() {
        return CurrentNameTextField;
    }

    public void setCurrentNameTextField(TextField currentNameTextField) {
        CurrentNameTextField = currentNameTextField;
    }

    public TextField getCurrentUserNameTextField() {
        return CurrentUserNameTextField;
    }

    public void setCurrentUserNameTextField(TextField currentUserNameTextField) {
        CurrentUserNameTextField = currentUserNameTextField;
    }

    public MFXButton getEmployeesButton() {
        return EmployeesButton;
    }

    public void setEmployeesButton(MFXButton employeesButton) {
        EmployeesButton = employeesButton;
    }

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

    public TextField getCurrentSalaryTextField() {
        return CurrentSalaryTextField;
    }

    public TextField getSalaryTextField() {
        return SalaryTextField;
    }

    public void setSalaryTextField(TextField salaryTextField) {
        SalaryTextField = salaryTextField;
    }

    public void setCurrentSalaryTextField(TextField currentSalaryTextField) {
        CurrentSalaryTextField = currentSalaryTextField;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public MFXButton getSettingsButton() {
        return SettingsButton;
    }

    public void setSettingsButton(MFXButton settingsButton) {
        SettingsButton = settingsButton;
    }


    public StackPane getAddUserStackPane() {
        return addUserStackPane;
    }

    public void setAddUserStackPane(StackPane addUserStackPane) {
        this.addUserStackPane = addUserStackPane;
    }

    public TextField getAgeTextField() {
        return ageTextField;
    }

    public void setAgeTextField(TextField ageTextField) {
        this.ageTextField = ageTextField;
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

    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public TextField getUserNameTextField() {
        return userNameTextField;
    }

    public void setUserNameTextField(TextField userNameTextField) {
        this.userNameTextField = userNameTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(TextField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }


    public TextField getRoleTextField() {
        return roleTextField;
    }

    public void setRoleTextField(TextField roleTextField) {
        this.roleTextField = roleTextField;
    }

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
        ScreenController.setMap( new HashMap<String, FXMLLoader>());
        FXMLLoader loader = new FXMLLoader(App.class.getResource("loginView/login-View.fxml"));
        loader.load();
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
        UserManager.getUserBag().forEach( e ->
                    e.getPayStubManager().addPayStub(e)
                );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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



            CurrentNameTextField.setText(UserManager.getCurrentUser().getName());
            CurrentUserNameTextField.setText(UserManager.getCurrentUser().getUsername());
            CurrentSalaryTextField.setText(String.valueOf(UserManager.getCurrentUser().getSalary()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
