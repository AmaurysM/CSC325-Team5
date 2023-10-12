package com.example.payrollapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.payrollapplication.Util.*;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 340);
        stage.setTitle("Roll Tide!");
        stage.setScene(scene);
        stage.show();
        UserBag.createUser("A","A","A",1,"A",1,"A");



    }

    public static void main(String[] args) {
        launch();
    }
}