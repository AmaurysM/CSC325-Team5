package com.csc325Team5.payrollapplication.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

public  class  ScreenController {
    private static HashMap<String, FXMLLoader> screenMap = new HashMap<>();

    private static FXMLLoader mainPaneLoader;
    private static Scene main;

    public ScreenController(Scene main) {
        this.main = main;
    }

    public static <T> void addScreen(String name, FXMLLoader loader) throws IOException {
        screenMap.put(name, loader);
    }

    public static void removeScreen(String name){
        screenMap.remove(name);
    }

    public static void activate(String name) throws IOException {
        /*if(findController("titleBar") == null){
            return;
        }*/
        // System.out.println(findController("titleBar"));
        //System.out.println("titleBar");

        TitleBarController controller = mainPaneLoader.getController();
        controller.getBorderPane().setCenter(screenMap.get(name).getRoot());
        // main.setRoot(screenMap.get(name).getRoot());
    }

    public static Scene getMain(){
        return main;
    }

    public static Pane find(String name) throws IOException {
        return screenMap.get(name).getRoot();
    }

    public static Object findController(String name){
        return screenMap.get(name).getController();
    }

    public static void setMap(HashMap<String, FXMLLoader> map){
        screenMap = map;
    }

    public static FXMLLoader getMainPaneLoader() {
        return mainPaneLoader;
    }

    public static void setMainPaneLoader(FXMLLoader mainPaneLoader) {
        ScreenController.mainPaneLoader = mainPaneLoader;
    }
}
