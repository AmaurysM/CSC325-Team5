package com.example.payrollapplication.controllers.managerViewControllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class notesTabController {

    @FXML
    private TableColumn<?, ?> NameColumn;

    @FXML
    private TableColumn<?, ?> NoteColumn;

    @FXML
    private MFXButton addNoteButton;

    @FXML
    private TableView<?> tableView;

    @FXML
    void addNewNote(ActionEvent event) {

    }

}
