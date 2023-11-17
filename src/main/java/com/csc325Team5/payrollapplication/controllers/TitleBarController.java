package com.csc325Team5.payrollapplication.controllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.loginViewControllers.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleBarController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    private boolean isResizing = false;
    private double resizeOffsetX = 0;
    private double resizeOffsetY = 0;


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

    @FXML
    void enlarge(MouseEvent event) {
        stage.setFullScreen(true);
    }

    @FXML
    void exit(MouseEvent event) {
        stage.close();
    }

    @FXML
    void minimize(MouseEvent event) {
        stage.setFullScreen(false);
    }

    @FXML
    void titleBarDragged(MouseEvent event) {
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    @FXML
    void titleBarPressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }


    @FXML
    void borderPaneDragged(MouseEvent event) {
        if (isResizing) {
            double newWidth = event.getX() + resizeOffsetX;
            double newHeight = event.getY() + resizeOffsetY;
            borderPane.setPrefSize(Math.max(newWidth, 100), Math.max(newHeight, 100));
        }
    }

    @FXML
    void borderPaneMouseMoved(MouseEvent event) {
        double border = 10; // Resize border width
        boolean onRightBorder = event.getX() > borderPane.getWidth() - border;
        boolean onBottomBorder = event.getY() > borderPane.getHeight() - border;

        if (onRightBorder && onBottomBorder) {
            borderPane.setStyle("-fx-border-width: 10 0 0 10; -fx-border-color: #3498db;");
        } else if (onRightBorder) {
            borderPane.setStyle("-fx-border-width: 0 0 0 10; -fx-border-color: #3498db;");
        } else if (onBottomBorder) {
            borderPane.setStyle("-fx-border-width: 10 0 0 0; -fx-border-color: #3498db;");
        } else {
            borderPane.setStyle("-fx-border-width: 0;");
        }
    }

    @FXML
    void borderPaneMousePressed(MouseEvent event) {
        double border = 10; // Resize border width
        boolean onRightBorder = event.getX() > borderPane.getWidth() - border;
        boolean onBottomBorder = event.getY() > borderPane.getHeight() - border;

        if (onRightBorder || onBottomBorder) {
            isResizing = true;
            resizeOffsetX = borderPane.getWidth() - event.getX();
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
