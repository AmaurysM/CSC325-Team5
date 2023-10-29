package com.example.payrollapplication;

import com.example.payrollapplication.controllers.ScreenController;
import com.example.payrollapplication.controllers.loginController;
import com.example.payrollapplication.model.UserBag;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // 2 basic users to check log into the manager view and employee view
        UserBag.createUser("a","a","a",0,0,"manager");
        UserBag.createUser("b","b","b",0,0,"employee");
        //

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PayRoll!!");
        ((loginController)fxmlLoader.getController()).setPrimaryStage(stage);

        new ScreenController(scene);
        ScreenController.addScreen("loginScreen", (Pane)scene.getRoot(),fxmlLoader.getController());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}