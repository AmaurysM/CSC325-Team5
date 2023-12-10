package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.controllers.TitleBarController;
import com.csc325Team5.payrollapplication.model.User;
import com.csc325Team5.payrollapplication.model.UserManager;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class SettingsTabController implements Initializable {

    @FXML
    private MFXButton editButton;

    @FXML
    private Label idLabel;

    @FXML
    private MFXButton logoutButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passwordTextField;


    @FXML
    void editCurrentUser(ActionEvent event) throws ExecutionException, InterruptedException {
        if(!allFieldsFilled()){
            return;
        }
        DocumentReference docRef = App.fstore.collection("Users").document(UserManager.getCurrentUser().getUsername());
        docRef.delete();

        UserManager.getCurrentUser().setName(nameTextField.getText());
        UserManager.getCurrentUser().setPassword(passwordTextField.getText());


        System.out.println("The just deleted user is "+ UserManager.getCurrentUser().getUsername());
        Map<String, Object> data = new HashMap<>();
        data.put("Name", nameTextField.getText());
        data.put("Age",  UserManager.getCurrentUser().getAge());
        data.put("User_Name",UserManager.getCurrentUser().getUsername());
        data.put("Password",passwordTextField.getText());
        data.put("Role", UserManager.getCurrentUser().getRole());
        data.put("Salary",UserManager.getCurrentUser().getSalary());
        data.put("ID", UserManager.getCurrentUser().getID());
        data.put("Notes",new ArrayList<>());
        data.put("SenderList",new ArrayList<>());

        ((ManagerController)ScreenController.findController("manager")).setUpInfo();
        DocumentReference newDocRef = App.fstore.collection("Users").document(UserManager.getCurrentUser().getUsername());
        newDocRef.set(data);
        ((EmployeesTabController)ScreenController.findController("employeesTab")).refreshTableView();
    }

    public boolean allFieldsFilled(){
        if(nameTextField.getText().isBlank()){
            return false;
        }


        if(passwordTextField.getText().isBlank()){
            return false;
        }

        return true;

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        ((ManagerController)ScreenController.findController("manager")).logoutOfManagerView(event);
    }

    public void setUpManagerView(){

        nameTextField.setText(UserManager.getCurrentUser().getName());
        passwordTextField.setText(UserManager.getCurrentUser().getPassword());
        idLabel.setText(UserManager.getCurrentUser().getID());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpManagerView();
    }
}
