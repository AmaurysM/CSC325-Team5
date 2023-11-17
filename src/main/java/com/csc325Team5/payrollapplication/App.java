package com.csc325Team5.payrollapplication;

import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.controllers.TitleBarController;
import com.csc325Team5.payrollapplication.controllers.loginViewControllers.LoginController;
import com.csc325Team5.payrollapplication.controllers.managerViewControllers.PopOverManager;
import com.csc325Team5.payrollapplication.model.UserManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class App extends Application {

    public static Scene scene;
    public static Stage stage;

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();
    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();

        // 2 basic users to log into the manager view and employee view
        UserManager.createUser("a","a","a",10,10,"manager");
        UserManager.createUser("b","b","b",20,20,"employee");
        //


        // System.out.println(ScreenController.find("titleBar"));

        setUpStage();
        stage.setScene(scene);
        stage.show();
    }

    public static void setUpStage() throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("titleBar.fxml"));
        App.scene = new Scene(fxmlLoader.load());
        TitleBarController controller = fxmlLoader.getController();
        controller.setStage(stage);

        new ScreenController(scene);
        ScreenController.setMainPaneLoader(fxmlLoader);

        FXMLLoader loader = new FXMLLoader(App.class.getResource("loginView/login-View.fxml"));
        loader.load();
        ((LoginController)loader.getController()).setPrimaryStage(stage);
        ScreenController.addScreen("loginView", loader);
        ScreenController.activate("loginView");

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