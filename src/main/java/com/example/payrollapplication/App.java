package com.example.payrollapplication;

import com.example.payrollapplication.controllers.ScreenController;
import com.example.payrollapplication.model.UserBag;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("manager-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PayRoll!!");

        ScreenController screenController = new ScreenController(scene);
        screenController.addScreen("loginScreen", FXMLLoader.load(getClass().getResource( "login-View.fxml" )));
        screenController.addScreen("managerView", FXMLLoader.load(getClass().getResource( "manager-View.fxml" )));
        screenController.addScreen("employeeView", FXMLLoader.load(getClass().getResource( "employee-View.fxml" )));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}