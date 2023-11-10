package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class PopOverController {

    @FXML
    private MFXButton deleteButton;

    @FXML
    private MFXButton editButton;

    @FXML
    private VBox vbox;

    private final EmployeesTabController employees = (EmployeesTabController) ScreenController.findController("employeesTab");


    @FXML
    void deleteUser(ActionEvent event) {
        //employeesTabController employees = (employeesTabController) ScreenController.getMapItem("employeesTab")[1];
        User user = employees.getTableView().getSelectionModel().getSelectedItem();
        employees.deleteUser(user);
        employees.getPopOver().hide();
    }

    @FXML
    void editUser(ActionEvent event) {
        //employeesTabController employees = (employeesTabController) ScreenController.getMapItem("employeesTab")[1];
        employees.editUser();
        employees.getPopOver().hide();
    }


}

