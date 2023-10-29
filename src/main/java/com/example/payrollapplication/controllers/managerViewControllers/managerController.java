package com.example.payrollapplication.controllers.managerViewControllers;

import com.example.payrollapplication.App;

import com.example.payrollapplication.controllers.ScreenController;
import com.example.payrollapplication.model.UserBag;
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
import java.util.ResourceBundle;

public class managerController implements Initializable {

    private Stage primaryStage;

    private ScreenController managerScreenController;

    @FXML
    private MFXButton EmployeesButton;

    @FXML
    private TextField FirstNameTextField;

    @FXML
    private TextField LastNameTextField;

    @FXML
    private MFXButton LogoutButton;

    @FXML
    private MFXButton NotesButton;

    @FXML
    private MFXButton PayrollButton;

    @FXML
    private TextField SalaryTextField;

    @FXML
    private MFXButton SettingsButton;

    @FXML
    private Button addNewUserButton;

    @FXML
    private StackPane addUserStackPane;

    @FXML
    private TextField ageTextField;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane centerAnchorPane;

    @FXML
    private TextField newNameTextField;

    @FXML
    private TextField newUserNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button quitAddingUserButton;

    @FXML
    private TextField roleTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    private StackPane stackPane;

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

    public TextField getFirstNameTextField() {
        return FirstNameTextField;
    }

    public void setFirstNameTextField(TextField firstNameTextField) {
        FirstNameTextField = firstNameTextField;
    }

    public TextField getLastNameTextField() {
        return LastNameTextField;
    }

    public void setLastNameTextField(TextField lastNameTextField) {
        LastNameTextField = lastNameTextField;
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

    public TextField getSalaryTextField() {
        return SalaryTextField;
    }

    public void setSalaryTextField(TextField salaryTextField) {
        SalaryTextField = salaryTextField;
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

    public Button getAddNewUserButton() {
        return addNewUserButton;
    }

    public void setAddNewUserButton(Button addNewUserButton) {
        this.addNewUserButton = addNewUserButton;
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

    public TextField getNewNameTextField() {
        return newNameTextField;
    }

    public void setNewNameTextField(TextField newNameTextField) {
        this.newNameTextField = newNameTextField;
    }

    public TextField getNewUserNameTextField() {
        return newUserNameTextField;
    }

    public void setNewUserNameTextField(TextField newUserNameTextField) {
        this.newUserNameTextField = newUserNameTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(TextField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public Button getQuitAddingUserButton() {
        return quitAddingUserButton;
    }

    public void setQuitAddingUserButton(Button quitAddingUserButton) {
        this.quitAddingUserButton = quitAddingUserButton;
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
        ScreenController.activate("loginScreen");

    }

    @FXML
    void quitAddingUser(ActionEvent event) {
        addUserStackPane.setVisible(false);
    }

    @FXML
    void createNewUser(ActionEvent event) {
        if(!allFieldsFilled()){
            return;
        }

        //String name, String username, String password, int age, String ID, int salary, String role
        UserBag.createUser(newNameTextField.getText(),
                newUserNameTextField.getText(),
                passwordTextField.getText(),
                Integer.valueOf(ageTextField.getText()),
                "0",
                Integer.valueOf(salaryTextField.getText()),
                roleTextField.getText());

        UserBag.printUsers();
        ((employeesTabController)ScreenController.getMapItem("employeesTab")[1]).getTableView().getItems().removeAll();
        ((employeesTabController)ScreenController.getMapItem("employeesTab")[1]).populateTableView();

        clearAllTextFields();
        addUserStackPane.setVisible(false);

    }

    public boolean allFieldsFilled(){
        if(newNameTextField.getText().isBlank()){
            clearAllTextFields();
            return false;
        }
        if(newUserNameTextField.getText().isBlank()){
            clearAllTextFields();
            return false;
        }
        if(passwordTextField.getText().isBlank()){
            clearAllTextFields();
            return false;
        }
        if(ageTextField.getText().isBlank()){
            clearAllTextFields();
            return false;
        }
        if(salaryTextField.getText().isBlank()){
            clearAllTextFields();
            return false;
        }
        if(roleTextField.getText().isBlank()){
            clearAllTextFields();
            return false;
        }
        return true;
    }

    public void clearAllTextFields(){
        newNameTextField.clear();
        newUserNameTextField.clear();
        passwordTextField.clear();
        ageTextField.clear();
        salaryTextField.clear();
        roleTextField.clear();
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

            addUserStackPane.setVisible(false);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
