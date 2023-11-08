package com.example.payrollapplication.controllers.managerViewControllers;

import com.example.payrollapplication.App;
import com.example.payrollapplication.controllers.ScreenController;
import com.example.payrollapplication.model.Note;
import com.example.payrollapplication.model.User;
import com.example.payrollapplication.model.UserBag;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class notesTabController implements Initializable {

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

        createNoteController noteController = (createNoteController)ScreenController.findController("createNote");
        managerController manager = (managerController)ScreenController.findController("manager");
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
        NameColumn.setCellValueFactory(new PropertyValueFactory<Note,String>("receiver"));
        NoteColumn.setCellValueFactory(new PropertyValueFactory<Note,String>("note"));
        populateTableView();
    }
}
