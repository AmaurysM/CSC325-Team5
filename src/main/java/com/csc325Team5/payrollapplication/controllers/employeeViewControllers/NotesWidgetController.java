package com.csc325Team5.payrollapplication.controllers.employeeViewControllers;

import com.csc325Team5.payrollapplication.model.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class NotesWidgetController {


    private NotesWidgetController controller;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label managerNameLabel;

    @FXML
    private TextFlow noteTextFlow;

    @FXML
    private Label timeLabel;

    @FXML
    private Region widgetRegion;

    public NotesWidgetController getController(){
        return controller;
    }

    @FXML
    void widgetPressed(MouseEvent event) {
        // What to do, what to do ...
        //System.out.println("Hello from widgetPressed();");
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }




    public void createWidget(Note note, NotesWidgetController controller){
        managerNameLabel.setText("Manager: "+note.getSender().getName());
        noteTextFlow.getChildren().add(new Text(note.getNote()));
        timeLabel.setText(note.getTime());
        this.controller = controller;
    }



}