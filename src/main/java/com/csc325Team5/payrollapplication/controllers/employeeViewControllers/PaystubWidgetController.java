package com.csc325Team5.payrollapplication.controllers.employeeViewControllers;

import com.csc325Team5.payrollapplication.model.PayStub;
import com.csc325Team5.payrollapplication.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.time.format.DateTimeFormatter;

public class PaystubWidgetController {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Label payDateLabel;

    @FXML
    private Label employeeIDLabel;

    @FXML
    private Label hoursLabel;

    @FXML
    private Label payperiodLabel;

    @FXML
    private Label salaryLabel;

    @FXML
    private Label totalLabel;

    public AnchorPane getAnchorPane() {
        return AnchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        AnchorPane = anchorPane;
    }

    public Label getPayDateLabel() {
        return payDateLabel;
    }

    public void setPayDateLabel(Label payDateLabel) {
        this.payDateLabel = payDateLabel;
    }

    public Label getEmployeeIDLabel() {
        return employeeIDLabel;
    }

    public void setEmployeeIDLabel(Label employeeIDLabel) {
        this.employeeIDLabel = employeeIDLabel;
    }

    public Label getHoursLabel() {
        return hoursLabel;
    }

    public void setHoursLabel(Label hoursLabel) {
        this.hoursLabel = hoursLabel;
    }

    public Label getPayperiodLabel() {
        return payperiodLabel;
    }

    public void setPayperiodLabel(Label payperiodLabel) {
        this.payperiodLabel = payperiodLabel;
    }

    public Label getSalaryLabel() {
        return salaryLabel;
    }

    public void setSalaryLabel(Label salaryLabel) {
        this.salaryLabel = salaryLabel;
    }

    public Label getTotalLabel() {
        return totalLabel;
    }

    public void setTotalLabel(Label totalLabel) {
        this.totalLabel = totalLabel;
    }

    public void createWidget(PayStub payStub) {
        this.payDateLabel.setText(payStub.getTimeCreated().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        this.employeeIDLabel.setText(payStub.getUser().getID());
        this.hoursLabel.setText(String.valueOf(payStub.getHoursWorked()));
        this.payperiodLabel.setText(payStub.getStartPayPeriod() + " - " + payStub.getEndPayPeriod());
        this.salaryLabel.setText(String.valueOf(payStub.getUser().getSalary()));
        this.totalLabel.setText("$" + payStub.getTotalPay());
    }







}
