package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;

import java.util.Comparator;
import java.util.Objects;

public class PopOverController{

    @FXML
    private MFXButton deleteButton;

    @FXML
    private MFXButton editButton;

    @FXML
    private VBox vbox;

    private final EmployeesTabController employees = (EmployeesTabController) ScreenController.findController("employeesTab");

    private User user;

    private PopOver popOver;

    @FXML
    void deleteUser(ActionEvent event) {
        User user = employees.getTableView().getSelectionModel().getSelectedItem();
        employees.deleteUser(user);
        employees.getPopOver().hide();
    }

    @FXML
    void editUser(ActionEvent event) {
        employees.editUser();
        employees.getPopOver().hide();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PopOver getPopOver() {
        return popOver;
    }

    public void setPopOver(PopOver popOver) {
        this.popOver = popOver;
    }

}

