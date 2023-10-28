package com.example.payrollapplication.controllers;

import com.example.payrollapplication.App;
import com.example.payrollapplication.controllers.managerViewControllers.employeesTabController;
import com.example.payrollapplication.controllers.managerViewControllers.managerController;
import com.example.payrollapplication.model.User;
import com.example.payrollapplication.model.UserBag;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class loginController implements Initializable{
    private Stage primaryStage;

    @FXML
    private MFXButton LoginButton;

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private MFXTextField usernameTextField;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML // This tries to find the user based on username and password.
    void validateUserNameAndPassword(ActionEvent event) throws IOException {
        User foundUser = UserBag.findUser(new User("0",usernameTextField.getText(),passwordField.getText(),0,"0",0,"0"));

        if(foundUser != null ){
            UserBag.setCurrentUser(foundUser);
            ScreenController.activate(UserBag.getCurrentUser().getRole());

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("managerView/manager-View.fxml"));
            ScreenController.addScreen("manager", loader.load(),loader.getController());
            ((managerController)(loader.getController())).setPrimaryStage(primaryStage);

            loader = new FXMLLoader(App.class.getResource("employeeView/employee-View.fxml"));
            ScreenController.addScreen("employee", loader.load(),loader.getController());



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
