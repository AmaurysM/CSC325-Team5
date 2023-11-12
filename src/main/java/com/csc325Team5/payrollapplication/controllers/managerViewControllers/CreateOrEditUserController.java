package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.User;
import com.csc325Team5.payrollapplication.model.UserBag;
import com.csc325Team5.payrollapplication.utilities.Role;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.jfoenix.controls.JFXComboBox;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.*;

public class CreateOrEditUserController implements Initializable {

    @FXML
    private TextField SalaryTextField;

    @FXML
    private StackPane stackPane;

    @FXML
    private TextField ageTextField;

    @FXML
    private MFXButton createOrEditUserButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private MFXButton quitCreatingOrEditingUserButton;

    @FXML
    private JFXComboBox<String> roleComboBox;

    @FXML
    private TextField userNameTextField;

    private ManagerController managerController;
    private EmployeesTabController employeesTabController;


    public TextField getSalaryTextField() {
        return SalaryTextField;
    }

    public void setSalaryTextField(TextField salaryTextField) {
        SalaryTextField = salaryTextField;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane addUserStackPane) {
        this.stackPane = addUserStackPane;
    }

    public TextField getAgeTextField() {
        return ageTextField;
    }

    public void setAgeTextField(TextField ageTextField) {
        this.ageTextField = ageTextField;
    }

    public MFXButton getCreateOrEditUserButton() {
        return createOrEditUserButton;
    }

    public void setCreateOrEditUserButton(MFXButton createOrEditUserButton) {
        this.createOrEditUserButton = createOrEditUserButton;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(TextField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public MFXButton getQuitAddingUserButton() {
        return quitCreatingOrEditingUserButton;
    }

    public void setQuitAddingUserButton(MFXButton quitAddingUserButton) {
        this.quitCreatingOrEditingUserButton = quitAddingUserButton;
    }

    public JFXComboBox<String> getRoleComboBox() {
        return roleComboBox;
    }

    public void setRoleComboBox(JFXComboBox<String> roleComboBox) {
        this.roleComboBox = roleComboBox;
    }

    public TextField getUserNameTextField() {
        return userNameTextField;
    }

    public void setUserNameTextField(TextField userNameTextField) {
        this.userNameTextField = userNameTextField;
    }

    @FXML
    public void createOrEditEmployees(ActionEvent event) {

        if(createOrEditUserButton.getText().toLowerCase().compareTo("add") == 0){
            createNewUser(event);
        }
        if(createOrEditUserButton.getText().toLowerCase().compareTo("edit") == 0){
            updateUser(event);
        }

    }

    public void createNewUser(ActionEvent event) {
        managerController = (ManagerController) ScreenController.findController("manager");

        if(!allFieldsFilled()){
            return;
        }

        addUser();
        UserBag.createUser(nameTextField.getText(),
                userNameTextField.getText(),
                passwordTextField.getText(),
                Integer.valueOf(ageTextField.getText()),
                Integer.valueOf(SalaryTextField.getText()),
                roleComboBox.getSelectionModel().getSelectedItem().toString());

        quitCreatingOrEditingUser(event);

    }

    public void updateUser(ActionEvent event){
        managerController = (ManagerController) ScreenController.findController("manager");
        employeesTabController = (EmployeesTabController) ScreenController.findController("employeesTab");

        if(!allFieldsFilled()){
            return;
        }
        System.out.println("updating");
        User user = (User) (employeesTabController).getTableView().getSelectionModel().getSelectedItem();

        UserBag.findUser(user).setName(nameTextField.getText());
        UserBag.findUser(user).setUsername(userNameTextField.getText());
        UserBag.findUser(user).setPassword(passwordTextField.getText());
        user.setAge(Integer.valueOf(ageTextField.getText()));
        user.setSalary(Integer.valueOf(SalaryTextField.getText()));
        UserBag.findUser(user).setRole(roleComboBox.getSelectionModel().getSelectedItem().toString());

        quitCreatingOrEditingUser(event);
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
        if(roleComboBox.getSelectionModel().isEmpty()){
            System.out.println(roleComboBox.getSelectionModel().isEmpty());
            return false;
        }

        return true;
    }

    public void addUser() {

        DocumentReference docRef = App.fstore.collection("Users").document(UUID.randomUUID().toString());

        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("Name", nameTextField.getText());
        data.put("User_Name",userNameTextField.getText());
        data.put("Password",passwordTextField.getText());
        data.put("Role",roleComboBox.getSelectionModel().getSelectedItem().toString());
        data.put("Salary",SalaryTextField.getText());

        data.put("Age", Integer.parseInt(ageTextField.getText()));
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);

        System.out.println("add your code here...");

        addUserToDatabase();
    }


    public void addUserToDatabase() {

        DocumentReference docRef = App.fstore.collection("Persons").document(UUID.randomUUID().toString());

        Map<String, Object> data = new HashMap<>();
        data.put("Name", nameTextField.getText());
        data.put("Age", Integer.parseInt(ageTextField.getText()));
        data.put("User_Name",userNameTextField.getText());
        data.put("Password",passwordTextField.getText());
        data.put("Role",roleComboBox.getSelectionModel().getSelectedItem().toString());
        data.put("Salary",SalaryTextField.getText());
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    public void foundUserInDatabase(){

    }

    public void clearAllTextFields(){
        nameTextField.clear();
        userNameTextField.clear();
        passwordTextField.clear();
        ageTextField.clear();
        SalaryTextField.clear();
        roleComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    void quitCreatingOrEditingUser(ActionEvent event) {
        managerController = (ManagerController) ScreenController.findController("manager");
        managerController.refreshTableView();
        managerController.getStackPane().getChildren().remove(stackPane);
        clearAllTextFields();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Arrays.stream(Role.values()).forEach( e -> {
            roleComboBox.getItems().add(e.name());
        });
    }
}
