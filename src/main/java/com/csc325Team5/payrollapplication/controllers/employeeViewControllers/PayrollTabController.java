package com.csc325Team5.payrollapplication.controllers.employeeViewControllers;
import com.csc325Team5.payrollapplication.App;
import com.csc325Team5.payrollapplication.model.Note;
import com.csc325Team5.payrollapplication.model.PayStub;
import com.csc325Team5.payrollapplication.model.User;
import com.csc325Team5.payrollapplication.model.UserManager;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PayrollTabController implements Initializable {

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

    public Node newPaystub(PayStub payStub){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("employeeView/paystubWidget.fxml"));
        try {
            loader.load();
            PaystubWidgetController controller = loader.getController();
            controller.createWidget(payStub);
            return controller.getAnchorPane();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserManager.getCurrentUser().getPayStubs().getPayStubs().forEach(e->{
            tilePane.getChildren().add(newPaystub(e));
        });
    }
}
