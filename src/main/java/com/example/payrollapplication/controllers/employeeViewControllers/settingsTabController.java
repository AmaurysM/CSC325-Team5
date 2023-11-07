package com.example.payrollapplication.controllers.employeeViewControllers;

import com.example.payrollapplication.controllers.ScreenController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class settingsTabController {

    @FXML
    private MFXButton logoutButton;

    @FXML
    void logout(ActionEvent event) throws IOException {
        ((employeeController)ScreenController.getMapItem("employee")[1]).logoutOfEmployeeView(event);
    }

}
