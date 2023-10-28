package com.example.payrollapplication.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Objects;

public class ScreenController {
    private static HashMap<String, Object[]> screenMap = new HashMap<>();
    private static Scene main;

    public ScreenController(Scene main) {
        this.main = main;
    }

    public static <T> void addScreen(String name,  Pane root, T controller) throws IOException {
        screenMap.put(name, new Object[]{root,controller});
    }

    public static void removeScreen(String name){
        screenMap.remove(name);
    }

    public static void activate(String name) throws IOException {

        main.setRoot((Pane) screenMap.get(name)[0]);
    }

    public static Scene getMain(){
        return main;
    }

    public static Pane find(String name) throws IOException {
        return (Pane)screenMap.get(name)[0];
    }


}
