package com.example.payrollapplication.controllers.managerViewControllers;

import com.example.payrollapplication.App;
import com.example.payrollapplication.model.UserBag;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class employeesTabController implements Initializable{

    @FXML
    private VBox UserCardHolderVBox;

    @FXML
    private MFXButton mainButton;

    @FXML
    private MFXTextField mainTextField;

    @FXML
    private VBox parentVBox;


    @FXML
    private MFXScrollPane mainmfxScrollPane;

    public VBox getUserCardHolderVBox() {
        return UserCardHolderVBox;
    }

    public void setUserCardHolderVBox(VBox userCardHolderVBox) {
        UserCardHolderVBox = userCardHolderVBox;
    }

    public MFXButton getMainButton() {
        return mainButton;
    }

    public void setMainButton(MFXButton mainButton) {
        this.mainButton = mainButton;
    }

    public MFXTextField getMainTextField() {
        return mainTextField;
    }

    public void setMainTextField(MFXTextField mainTextField) {
        this.mainTextField = mainTextField;
    }

    public VBox getParentVBox() {
        return parentVBox;
    }

    public void setParentVBox(VBox parentVBox) {
        this.parentVBox = parentVBox;
    }

    public MFXScrollPane getMainmfxScrollPane() {
        return mainmfxScrollPane;
    }

    public void setMainmfxScrollPane(MFXScrollPane mainmfxScrollPane) {
        this.mainmfxScrollPane = mainmfxScrollPane;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserBag.getUserBag().forEach( (e)->{

            try {
                FXMLLoader employeesCardLoader = new FXMLLoader(App.class.getResource("employeeCard-View.fxml"));
                employeesCardLoader.load();
                UserCardHolderVBox.getChildren().add((employeesCardLoader.getRoot()));

                ((employeeCardController)(employeesCardLoader.getController())).getUserNameLabel().setText(e.getName());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        );
    }
}
