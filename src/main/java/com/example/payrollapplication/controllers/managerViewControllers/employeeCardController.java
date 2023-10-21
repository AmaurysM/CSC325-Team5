package com.example.payrollapplication.controllers.managerViewControllers;

import com.example.payrollapplication.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class employeeCardController implements Initializable {

    @FXML
    private Label UserNameLabel;

    @FXML
    private HBox parentUserCardHBox;

    @FXML
    private Circle profilePictureCircle;

    public Label getUserNameLabel() {
        return UserNameLabel;
    }

    public void setUserNameLabel(Label userNameLabel) {
        UserNameLabel = userNameLabel;
    }

    public HBox getParentUserCardHBox() {
        return parentUserCardHBox;
    }

    public void setParentUserCardHBox(HBox parentUserCardHBox) {
        this.parentUserCardHBox = parentUserCardHBox;
    }

    public Circle getProfilePictureCircle() {
        return profilePictureCircle;
    }

    public void setProfilePictureCircle(Circle profilePictureCircle) {
        this.profilePictureCircle = profilePictureCircle;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
