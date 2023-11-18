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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleBarController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    private boolean isResizing = false;
    private double resizeOffsetX = 0;
    private double resizeOffsetY = 0;

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
        stage.close();
    }

    @FXML
    void minimize(MouseEvent event) {
        stage.setIconified(true);
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
    void borderPaneDragged(MouseEvent event) throws IOException {
        if (!isResizing) {
            return;
        }

        double newWidth = event.getX() + resizeOffsetX;
        double newHeight = event.getY() + resizeOffsetY;

        if(newWidth < 150 || newHeight < 45){
            return;
        }
        stage.getScene().getWindow().setWidth(newWidth);
        stage.getScene().getWindow().setHeight(newHeight);
    }

    @FXML
    void borderPaneMouseMoved(MouseEvent event) {
        double border = 10; // Resize border width
        onRightBorder = event.getX() > borderPane.getWidth() - border;
        onLeftBorder = event.getX() < border;
        onBottomBorder = event.getY() > borderPane.getHeight() - border;
        onTopBorder = event.getY() < border;

        if (onRightBorder && onBottomBorder) {
            //borderPane.setStyle("-fx-border-width: 1 0 0 1; -fx-border-color: #3498db;");
            stage.getScene().setCursor(Cursor.SE_RESIZE);
        } else if (onRightBorder) {
            //borderPane.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: #3498db;");
            stage.getScene().setCursor(Cursor.H_RESIZE);
        } else if (onBottomBorder) {
            //borderPane.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: #3498db;");
            stage.getScene().setCursor(Cursor.V_RESIZE);
        } else if (onLeftBorder) {
            //borderPane.setStyle("-fx-border-width: 0 0 0 1; -fx-border-color: #3498db;");
            stage.getScene().setCursor(Cursor.H_RESIZE);
        } else if (onTopBorder) {
            //borderPane.setStyle("-fx-border-width: 1 0 0 0; -fx-border-color: #3498db;");
            stage.getScene().setCursor(Cursor.V_RESIZE);
        }else {
            //borderPane.setStyle("-fx-border-width: 0;");
            stage.getScene().setCursor(Cursor.DEFAULT);
        }

    }

    @FXML
    void borderPaneMousePressed(MouseEvent event) {

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
