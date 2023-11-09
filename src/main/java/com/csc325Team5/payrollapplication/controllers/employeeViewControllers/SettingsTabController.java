package com.csc325Team5.payrollapplication.controllers.employeeViewControllers;

import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.UserBag;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsTabController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private Label iDLabel;

    @FXML
    private MFXButton logoutButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    void logout(ActionEvent event) throws IOException {
        ((EmployeeController) ScreenController.findController("employee")).logoutOfEmployeeView(event);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iDLabel.setText(UserBag.getCurrentUser().getID());
        nameLabel.setText(UserBag.getCurrentUser().getName());
        usernameLabel.setText(UserBag.getCurrentUser().getUsername());
    }
}

