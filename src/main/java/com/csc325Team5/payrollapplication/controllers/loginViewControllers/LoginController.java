package com.csc325Team5.payrollapplication.controllers.loginViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.controllers.employeeViewControllers.EmployeeController;
import com.csc325Team5.payrollapplication.controllers.managerViewControllers.ManagerController;
import com.csc325Team5.payrollapplication.model.UserManager;
import com.csc325Team5.payrollapplication.model.User;
import com.csc325Team5.payrollapplication.utilities.Role;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class LoginController implements Initializable {
    private Stage primaryStage;

    @FXML
    private MFXButton LoginButton;

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private MFXTextField usernameTextField;

    @FXML
    private Label passwordWarningLabel;

    @FXML
    private Label usernameWarningLabel;

    @FXML
    private Label warningLabel;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    void loginKey(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            login();
        }
    }

    public void clearFields(){
        usernameTextField.clear();
        passwordField.clear();
    }

    @FXML
        // This tries to find the user based on username and password.
    void loginButtonPressed(ActionEvent event) throws IOException {
        login();
    }

    public void login() throws IOException {

        ApiFuture<QuerySnapshot> future =  App.fstore.collection("Users").get();
        List<QueryDocumentSnapshot> documents;
        User user1 = new User();
        Boolean foundUser = false;
        try
        {
            documents = future.get().getDocuments();
            if(documents.size()>0)
            {

                for (QueryDocumentSnapshot document : documents)
                {

                    if(document.getData().get("User_Name").equals(usernameTextField.getText()))
                    {
                        if(document.getData().get("User_Name").equals(usernameTextField.getText())
                                && document.getData().get("Password").equals(passwordField.getText()))
                        {
                            System.out.println("user found");
                            user1.setName((String) document.getData().get("Name"));
                            user1.setUsername((String) document.getData().get("User_Name"));
                            user1.setPassword((String) document.getData().get("Password"));
                            user1.setRole((String) document.getData().get("Role"));
                            foundUser = true;

                        }
                        else
                        {
                            warningLabel.setVisible(true);
                            warningLabel.setText("THIS USERNAME AND PASSWORD DON'T MATCH");
                            return;
                        }

                    }


                }
                if(!foundUser)
                {
                    warningLabel.setVisible(true);
                    warningLabel.setText("THIS USER DOES NOT EXIST");
                    System.out.println("in the else block");
                    return;

                }
            }

        }
        catch (InterruptedException | ExecutionException ex)
        {
            ex.printStackTrace();
        }


//        User foundUser = UserManager.findUser(new User("0", usernameTextField.getText(), passwordField.getText(), 0, 0, null,"0"));

        if(!ableToLogin(user1,usernameTextField.getText())){
            return;
        }
        warningLabel.setVisible(false);

        clearFields();
        UserManager.setCurrentUser(user1);
        loadViews();
    }

    public boolean ableToLogin(User user, String username){

        if(!allFieldsFilled()){
            return false;
        }
//        if(!usernameExists(username)){
//            warningLabel.setVisible(true);
//            warningLabel.setText("THIS USER DOES NOT EXIST");
//            return false;
//        }
//
//        if (user == null) {
//            warningLabel.setVisible(true);
//            warningLabel.setText("THIS USERNAME AND PASSWORD DON'T MATCH");
//            return false;
//        }



        return true;
    }

    public boolean usernameExists(String username){
        return !UserManager.findUserByName(username).toList().isEmpty();
    }

    public boolean allFieldsFilled(){
        usernameWarningLabel.setVisible(false);
        passwordWarningLabel.setVisible(false);
        warningLabel.setVisible(false);

        if(usernameTextField.getText().isEmpty()){
            usernameWarningLabel.setVisible(true);
            return false;
        }

        if(passwordField.getText().isEmpty()){
            passwordWarningLabel.setVisible(true);
            return false;
        }

        return true;

    }

    public void loadViews() throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("managerView/manager-View.fxml"));

        if (Role.MANAGER.name().compareTo(UserManager.getCurrentUser().getRole().toUpperCase()) == 0) {
            loader.load();
            ScreenController.addScreen("manager", loader);
            ((ManagerController) (loader.getController())).setPrimaryStage(primaryStage);
            ScreenController.activate("manager");

        } else {
            loader = new FXMLLoader(App.class.getResource("employeeView/employee-View.fxml"));
            loader.load();
            ((EmployeeController) (loader.getController())).setPrimaryStage(primaryStage);
            ScreenController.addScreen("employee", loader);
            ScreenController.activate("employee");

        }
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //System.out.println(primaryStage);


    }
}
