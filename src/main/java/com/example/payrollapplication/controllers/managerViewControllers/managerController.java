package com.example.payrollapplication.controllers.managerViewControllers;

import com.example.payrollapplication.App;

import com.example.payrollapplication.controllers.ScreenController;
import com.example.payrollapplication.model.User;
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
    private MFXButton editUserButton;

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
    private MFXButton quitAddingUserButton;

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

    public MFXButton getEditUserButton() {
        return editUserButton;
    }

    public void setAddNewUserButton(MFXButton addNewUserButton) {
        this.editUserButton = addNewUserButton;
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

    public MFXButton getQuitAddingUserButton() {
        return quitAddingUserButton;
    }

    public void setQuitAddingUserButton(MFXButton quitAddingUserButton) {
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
    public void editEmployees(ActionEvent event) {

        if(editUserButton.getText().toLowerCase().compareTo("add") == 0){
            createNewUser();
            return;
        }
        if(editUserButton.getText().toLowerCase().compareTo("edit") == 0){
            updateUser();
            return;

        }

    }

    public void createNewUser() {
        if(!allFieldsFilled()){
            return;
        }

        //String name, String username, String password, int age, String ID, int salary, String role
        UserBag.createUser(nameTextField.getText(),
                userNameTextField.getText(),
                passwordTextField.getText(),
                Integer.valueOf(ageTextField.getText()),
                Integer.valueOf(SalaryTextField.getText()),
                roleTextField.getText());

        refreshTableView();
        clearAllTextFields();
        addUserStackPane.setVisible(false);

    }


    public void updateUser(){
        if(!allFieldsFilled()){
            return;
        }

        employeesTabController controller = ((employeesTabController) ScreenController.getMapItem("employeesTab")[1]);
        User user = (User) (controller).getTableView().getSelectionModel().getSelectedItem();

        UserBag.findUser(user).setName(nameTextField.getText());
        UserBag.findUser(user).setUsername(userNameTextField.getText());
        UserBag.findUser(user).setPassword(passwordTextField.getText());
        user.setAge(Integer.valueOf(ageTextField.getText()));
        user.setSalary(Integer.valueOf(SalaryTextField.getText()));
        UserBag.findUser(user).setRole(roleTextField.getText());

        refreshTableView();
        clearAllTextFields();
        addUserStackPane.setVisible(false);
    }
    public void refreshTableView(){
        employeesTabController controller = ((employeesTabController)ScreenController.getMapItem("employeesTab")[1]);
        controller.refreshTableView();

    }

    public boolean allFieldsFilled(){
        if(nameTextField.getText().isBlank()){
            return false;
        }
        if(userNameTextField.getText().isBlank()){
            return false;
        }
        if(passwordTextField.getText().isBlank()){
            return false;
        }
        if(ageTextField.getText().isBlank()){
            return false;
        }
        if(SalaryTextField.getText().isBlank()){
            return false;
        }
        if(roleTextField.getText().isBlank()){

            return false;
        }

        return true;
    }

    public void clearAllTextFields(){
        nameTextField.clear();
        userNameTextField.clear();
        passwordTextField.clear();
        ageTextField.clear();
        SalaryTextField.clear();
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

            CurrentNameTextField.setText(UserBag.getCurrentUser().getName());
            CurrentUserNameTextField.setText(UserBag.getCurrentUser().getUsername());
            CurrentSalaryTextField.setText(String.valueOf(UserBag.getCurrentUser().getSalary()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
