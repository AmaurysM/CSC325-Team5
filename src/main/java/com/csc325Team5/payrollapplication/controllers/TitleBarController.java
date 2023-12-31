package com.csc325Team5.payrollapplication.controllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.employeeViewControllers.EmployeeController;
import com.csc325Team5.payrollapplication.controllers.loginViewControllers.LoginController;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleBarController implements Initializable  {

    private double xOffset = 0;
    private double yOffset = 0;

    private boolean isResizing = false;
    private double resizeOffsetX = 0;
    private double resizeOffsetY = 0;
    private double rightSideOfScreenX = 0;
    private double topOfScreenY = 0;
    private boolean onRightBorder;
    private boolean onLeftBorder;
    private boolean onBottomBorder;
    private boolean onTopBorder;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Pane closePageButton;

    @FXML
    private Pane enlargePageButton;

    @FXML
    private Pane minimizePageButton;

    @FXML
    private HBox titleBar;

    @FXML
    private Label titleLable;

    private Stage stage;

    public Pane getClosePageButton() {
        return closePageButton;
    }

    public void setClosePageButton(Pane closePageButton) {
        this.closePageButton = closePageButton;
    }

    public Pane getEnlargePageButton() {
        return enlargePageButton;
    }

    public void setEnlargePageButton(Pane enlargePageButton) {
        this.enlargePageButton = enlargePageButton;
    }

    public Pane getMinimizePageButton() {
        return minimizePageButton;
    }

    public void setMinimizePageButton(Pane minimizePageButton) {
        this.minimizePageButton = minimizePageButton;
    }

    public HBox getTitleBar() {
        return titleBar;
    }

    public void setTitleBar(HBox titleBar) {
        this.titleBar = titleBar;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public Label getTitleLable() {
        return titleLable;
    }

    public void setTitleLable(Label titleLable) {
        this.titleLable = titleLable;
    }

    @FXML
    void enlarge(MouseEvent event) {

        if(stage.isMaximized()){
            stage.setMaximized(false);
            return;
        }
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setMaximized(true);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

    }

    @FXML
    void exit(MouseEvent event) {
        if(!isResizing) {
            stage.close();
        }
    }

    @FXML
    void minimize(MouseEvent event) {
        stage.setIconified(true);
    }

    @FXML
    void titleBarDragged(MouseEvent event) {
        if(stage.isMaximized()){
            stage.setMaximized(false);
        }
        if(!isResizing) {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        }
    }

    @FXML
    void titleBarPressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }


    @FXML
    void borderPaneDragged(MouseEvent event) throws IOException {
        if (!isResizing) {
            return;
        }
        
        if(onRightBorder){
            double newWidth = event.getX() + resizeOffsetX;

            if(newWidth < 135){
                return;
            }
            stage.getScene().getWindow().setWidth(newWidth);

        }
        if(onBottomBorder){
            double newHeight = event.getY() + resizeOffsetY;

            if(newHeight < 30){
                return;
            }
            stage.getScene().getWindow().setHeight(newHeight);
        }

        if(onLeftBorder){
            double valueX = event.getX();
            double width = stage.getWidth();
            double newRightSideOfScreenX = event.getScreenX() + stage.getWidth();
            double newWidth = width + (rightSideOfScreenX - newRightSideOfScreenX);

            if(width-valueX < 135 ){
                return;
            }

            stage.setX(event.getScreenX() - xOffset);
            stage.setWidth(newWidth);
        }

        if(onTopBorder){
            double valueY = event.getY();
            double height = stage.getScene().getWindow().getHeight();
            double newTopOfScreenY = event.getScreenY() + stage.getHeight();
            double newHeight = height + (topOfScreenY - newTopOfScreenY);

            if(height-valueY < 30){
                return;
            }

            stage.setY(event.getScreenY() - yOffset);
            stage.setHeight(newHeight);
        }

    }

    @FXML
    void borderPaneMouseMoved(MouseEvent event) {
        double border = 3;
        onRightBorder = event.getX() > borderPane.getWidth() - border;
        onLeftBorder = event.getX() < border;
        onBottomBorder = event.getY() > borderPane.getHeight() - border;
        onTopBorder = event.getY() < border;

        if (onRightBorder && onBottomBorder) {
            stage.getScene().setCursor(Cursor.SE_RESIZE);
        } else if(onLeftBorder && onBottomBorder){
            stage.getScene().setCursor(Cursor.NE_RESIZE);
        }else if(onLeftBorder && onTopBorder){
            stage.getScene().setCursor(Cursor.SE_RESIZE);
        }else if(onTopBorder && onRightBorder){
            stage.getScene().setCursor(Cursor.NE_RESIZE);
        } else if (onRightBorder) {
            stage.getScene().setCursor(Cursor.H_RESIZE);
        } else if (onBottomBorder) {
            stage.getScene().setCursor(Cursor.V_RESIZE);
        } else if (onLeftBorder) {
            stage.getScene().setCursor(Cursor.H_RESIZE);
        } else if (onTopBorder) {
            stage.getScene().setCursor(Cursor.V_RESIZE);
        }else {
            stage.getScene().setCursor(Cursor.DEFAULT);
        }

    }

    @FXML
    void borderPaneMousePressed(MouseEvent event) {


        if (onRightBorder) {
            isResizing = true;
            resizeOffsetX = borderPane.getWidth() - event.getX();

        }

        if (onBottomBorder){
            isResizing = true;
            resizeOffsetY = borderPane.getHeight() - event.getY();
        }

        if(onLeftBorder){
            isResizing = true;
            xOffset = event.getSceneX();
            rightSideOfScreenX = event.getScreenX() + stage.getWidth();
            resizeOffsetX = borderPane.getWidth() - event.getX();
        }

        if(onTopBorder){
            isResizing = true;
            yOffset = event.getSceneY();
            topOfScreenY = event.getScreenY() + stage.getHeight();
            resizeOffsetY = borderPane.getHeight() - event.getY();
        }



    }

    @FXML
    void borderPaneMouseReleased(MouseEvent event) {
        isResizing = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void start() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("loginView/login-View.fxml"));
            loader.load();
            ((LoginController)loader.getController()).setPrimaryStage(stage);
            ScreenController.addScreen("loginView", loader);
            ScreenController.activate("loginView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
