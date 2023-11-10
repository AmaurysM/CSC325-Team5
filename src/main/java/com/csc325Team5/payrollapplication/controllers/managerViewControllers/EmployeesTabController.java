package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.UserBag;
import com.csc325Team5.payrollapplication.utilities.Role;
import javafx.fxml.FXMLLoader;
import org.controlsfx.control.PopOver;
import com.csc325Team5.payrollapplication.model.User;
import io.github.palexdev.materialfx.controls.MFXButton;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesTabController implements Initializable {


    private Stage primaryStage;

    @FXML
    private TableColumn<User, String> NameColumn;

    @FXML
    private TableColumn<User, String> RoleColumn;

    @FXML
    private TableColumn<User, Integer> SalaryColumn;

    @FXML
    private TableColumn<User, String> IDColumn;

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
    void getSelectedUser(MouseEvent  event) throws IOException {

        if(event.getClickCount() == 2){
            User selectedUser = tableView.getSelectionModel().getSelectedItem();
            createPopupWindow(selectedUser, event);
        }
    }
    private PopOver popOver;

    public PopOver getPopOver() {
        return popOver;
    }

    public void setPopOver(PopOver popOver) {
        this.popOver = popOver;
    }

    private void createPopupWindow(User user, MouseEvent event) throws IOException {

        popOver = new PopOver();

        if(!popOver.isShowing() && !popOver.isDetached()) {

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("managerView/popOver.fxml"));
            fxmlLoader.load();

            popOver.setContentNode(fxmlLoader.getRoot());
            popOver.show(tableView, event.getScreenX(), event.getScreenY());

        }

    }

    void deleteUser(User user){
        UserBag.removeUser(user);
        refreshTableView();
    }

    void editUser(){// runs when you hit the user edit button on the drop-down menu
        CreateOrEditUserController createOrEditUserController = ((CreateOrEditUserController) ScreenController.findController("createOrEditUser"));
        ManagerController managerController = (ManagerController) ScreenController.findController("manager");
        //createUserController.().setVisible(true);

        User userToBeEdited = tableView.getSelectionModel().getSelectedItem();
        createOrEditUserController.getNameTextField().setText(userToBeEdited.getName());
        createOrEditUserController.getUserNameTextField().setText(userToBeEdited.getUsername());
        createOrEditUserController.getPasswordTextField().setText(userToBeEdited.getPassword());
        createOrEditUserController.getAgeTextField().setText(String.valueOf(userToBeEdited.getAge()));
        createOrEditUserController.getRoleComboBox().getSelectionModel().select(userToBeEdited.getRole().toUpperCase());
        createOrEditUserController.getSalaryTextField().setText( String.valueOf(userToBeEdited.getSalary()));

        createOrEditUserController.getCreateOrEditUserButton().setText("EDIT");

        managerController.getStackPane().getChildren().add(createOrEditUserController.getStackPane());


    }

    @FXML
    void addNewUser(ActionEvent event) {
        CreateOrEditUserController createOrEditUserController = ((CreateOrEditUserController)ScreenController.findController("createOrEditUser"));
        ManagerController managerController = (ManagerController) ScreenController.findController("manager");
        managerController.getStackPane().getChildren().add(createOrEditUserController.getStackPane());
        createOrEditUserController.getCreateOrEditUserButton().setText("ADD");
    }

    public void populateTableView(){
        tableView.setItems(
            FXCollections.observableArrayList(UserBag.getUserBag().stream().toList()).filtered(e->
                Role.MANAGER.name().compareTo(e.getRole().toUpperCase()) != 0
            )
        );
    }

    public void refreshTableView() {
        tableView.refresh();
        populateTableView();
        tableView.sort();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        SalaryColumn.setCellValueFactory(new PropertyValueFactory<User,Integer>("salary"));
        RoleColumn.setCellValueFactory(new PropertyValueFactory<User,String>("role"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<User,String>("ID"));
        populateTableView();
    }


}
