package com.csc325Team5.payrollapplication;

import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.controllers.LoginController;
import com.csc325Team5.payrollapplication.controllers.managerViewControllers.PopOverManager;
import com.csc325Team5.payrollapplication.model.UserBag;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;

public class App extends Application {

    public static Scene scene;
    public static Stage stage;

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();

        // 2 basic users to log into the manager view and employee view
        UserBag.createUser("a","a","a",0,0,"manager");
        UserBag.createUser("b","b","b",0,0,"employee");
        //

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PayRoll!!");
        ((LoginController)fxmlLoader.getController()).setPrimaryStage(stage);
        setUp();

        new ScreenController(scene);
        ScreenController.addScreen("loginScreen", (Pane)scene.getRoot(),fxmlLoader.getController());

        stage.setScene(scene);
        stage.show();
    }

    public static void setUp(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                if(PopOverManager.getPop() != null){
                    event.consume();
                    PopOverManager.getPop().getPopOver().hide(new Duration(0));
                    stage.close();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}