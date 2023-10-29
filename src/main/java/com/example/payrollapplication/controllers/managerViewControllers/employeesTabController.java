package com.example.payrollapplication.controllers.managerViewControllers;

import com.example.payrollapplication.controllers.ScreenController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.controlsfx.control.PopOver;
import com.example.payrollapplication.App;
import com.example.payrollapplication.model.User;
import com.example.payrollapplication.model.UserBag;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class employeesTabController implements Initializable {


    private Stage primaryStage;

    @FXML
    private TableColumn<User, String> NameColumn;

    @FXML
    private TableColumn<User, String> RoleColumn;

    @FXML
    private TableColumn<User, Integer> SalaryColumn;

    @FXML
    private MFXButton addButton;

    @FXML
    private StackPane centerPane;

    @FXML
    private TableView<User> tableView;

    @FXML
    private AnchorPane anchorPane;

    public TableColumn<User, String> getNameColumn() {
        return NameColumn;
    }

    public void setNameColumn(TableColumn<User, String> nameColumn) {
        NameColumn = nameColumn;
    }

    public TableColumn<User, String> getRoleColumn() {
        return RoleColumn;
    }

    public void setRoleColumn(TableColumn<User, String> roleColumn) {
        RoleColumn = roleColumn;
    }

    public TableColumn<User, Integer> getSalaryColumn() {
        return SalaryColumn;
    }

    public void setSalaryColumn(TableColumn<User, Integer> salaryColumn) {
        SalaryColumn = salaryColumn;
    }

    public MFXButton getAddButton() {
        return addButton;
    }

    public void setAddButton(MFXButton addButton) {
        this.addButton = addButton;
    }

    public StackPane getCenterPane() {
        return centerPane;
    }

    public void setCenterPane(StackPane centerPane) {
        this.centerPane = centerPane;
    }

    public TableView<User> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<User> tableView) {
        this.tableView = tableView;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Stage getPrimaryStage() {

        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
    }

    @FXML
    void getSelectedUser(MouseEvent  event) {

        if(event.getClickCount() == 2){
            User selectedUser = tableView.getSelectionModel().getSelectedItem();
            createPopupWindow(selectedUser, event);

        }
    }

    private void createPopupWindow(User user, MouseEvent event){

        PopOver popOver = new PopOver();

        if(!popOver.isShowing() && !popOver.isDetached()) {
            VBox content = new VBox();

            Button actionButton = new Button("Delete");

            actionButton.setOnAction((e) -> {
                deleteUser(user);
                popOver.hide();
            });


            content.getChildren().addAll(actionButton);
            popOver.setContentNode(content);
            popOver.show(tableView, event.getScreenX(), event.getScreenY());

        }


    }

    void deleteUser(User user){
        UserBag.removeUser(user);
        tableView.getItems().remove(user);
    }

    @FXML
    void addNewUser(ActionEvent event) {
        ((managerController)ScreenController.getMapItem("manager")[1]).getAddUserStackPane().setVisible(true);
    }

    public void populateTableView(){
        tableView.setItems(FXCollections.observableArrayList(UserBag.getUserBag().stream().toList()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        NameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        SalaryColumn.setCellValueFactory(new PropertyValueFactory<User,Integer>("salary"));
        RoleColumn.setCellValueFactory(new PropertyValueFactory<User,String>("role"));
        populateTableView();
    }


}
