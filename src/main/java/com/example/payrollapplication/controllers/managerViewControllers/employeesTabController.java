package com.example.payrollapplication.controllers.managerViewControllers;

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

    public Stage getPrimaryStage() {

        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    void getSelectedUser(MouseEvent  event) {
        //System.out.println(tableView.getSelectionModel().getSelectedItem());
        if(event.getClickCount() == 2){
            User selectedUser = tableView.getSelectionModel().getSelectedItem();
            createPopupWindow(selectedUser, event);
            System.out.println("Do it");
        }//
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
        /*popupOver.setContentNode(createPopOver);*/

    }

    void deleteUser(User user){
        UserBag.removeUser(user);
        tableView.getItems().remove(user);
    }

    @FXML
    void addNewUser(ActionEvent event) {
        System.out.println("+ADD+");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*UserBag.getUserBag().forEach((e) -> {

            }
        );*/
        NameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        SalaryColumn.setCellValueFactory(new PropertyValueFactory<User,Integer>("salary"));
        RoleColumn.setCellValueFactory(new PropertyValueFactory<User,String>("role"));
        tableView.setItems(FXCollections.observableArrayList(UserBag.getUserBag().stream().toList()));


    }


}
