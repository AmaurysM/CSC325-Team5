package com.csc325Team5.payrollapplication.controllers.employeeViewControllers;

import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.model.UserBag;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotesTabController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private MFXButton searchButton;

    @FXML
    private MFXTextField textField;

    @FXML
    private TilePane tilePane;

    @FXML
    void search(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserBag.getCurrentUser().getNotes().stream().forEach((e)->{

            FXMLLoader loader = new FXMLLoader(App.class.getResource("employeeView/notesWidget.fxml"));
            try {
                loader.load();
                NotesWidgetController controller = loader.getController();
                controller.createWidget(e,loader.getController());
                tilePane.getChildren().add(controller.getGridPane());

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });
    }
}

