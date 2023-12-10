package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

import com.csc325Team5.payrollapplication.controllers.ScreenController;
import com.csc325Team5.payrollapplication.model.UserManager;
import com.csc325Team5.payrollapplication.model.Note;
import com.csc325Team5.payrollapplication.utilities.Role;
import io.github.palexdev.materialfx.controls.MFXButton;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Note, String> TimeColumn;

    @FXML
    private MFXButton addNoteButton;

    @FXML
    private TableView<Note> tableView;

    @FXML
    void addNewNote(ActionEvent event) throws IOException {

        CreateNoteController noteController = (CreateNoteController) ScreenController.findController("createNote");
        ManagerController manager = (ManagerController)ScreenController.findController("manager");
        manager.getStackPane().getChildren().add(noteController.getAnchorPane());

        noteController.getEmployeeComboBox().getItems().remove(0,noteController.getEmployeeComboBox().getItems().size());

        UserManager.getUserBag().stream().filter(e -> Role.MANAGER.name().compareTo(e.getRole().toUpperCase()) != 0)
                .forEach(e->  noteController.getEmployeeComboBox().getItems().add(e));

    }

    public void populateTableView(){
        tableView.setItems(FXCollections.observableArrayList(UserManager.getCurrentUser().getNoteManager().getNotes()));
    }

    public void refreshTableView(){

        tableView.refresh();
        populateTableView();
        tableView.sort();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NameColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getReceiver()));
        NoteColumn.setCellValueFactory(new PropertyValueFactory<Note,String>("note"));
        IDColumn.setCellValueFactory((new PropertyValueFactory<Note,String>("receiverID")));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Note,String>("time"));
        populateTableView();
    }
}
