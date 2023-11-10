package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.UserBag;
import com.csc325Team5.payrollapplication.model.Note;
import io.github.palexdev.materialfx.controls.MFXButton;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotesTabController implements Initializable {

    @FXML
    private TableColumn<Note, String> IDColumn;

    @FXML
    private TableColumn<Note, String> NameColumn;

    @FXML
    private TableColumn<Note, String> NoteColumn;

    @FXML
    private MFXButton addNoteButton;

    @FXML
    private TableView<Note> tableView;

    @FXML
    void addNewNote(ActionEvent event) throws IOException {

        CreateNoteController noteController = (CreateNoteController) ScreenController.findController("createNote");
        ManagerController manager = (ManagerController)ScreenController.findController("manager");
        manager.getStackPane().getChildren().add(noteController.getAnchorPane());
    }

    public void populateTableView(){
        tableView.setItems(FXCollections.observableArrayList(UserBag.getCurrentUser().getNotes()));
    }

    public void refreshTableView(){

        tableView.refresh();
        populateTableView();
        tableView.sort();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Note, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Note, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getReceiver().getName());
            }
        });
        NoteColumn.setCellValueFactory(new PropertyValueFactory<Note,String>("note"));
        IDColumn.setCellValueFactory((new PropertyValueFactory<Note, String>("receiverID") ));
        populateTableView();
    }
}
