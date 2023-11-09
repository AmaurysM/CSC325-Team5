module com.example.payrollapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires google.cloud.firestore;
    requires com.google.api.apicommon;
    requires google.cloud.core;
    requires com.fasterxml.jackson.core;

    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires eu.hansolo.fx.countries;
    requires MaterialFX;
    requires controlsfx;
    requires com.jfoenix;

    opens com.csc325Team5.payrollapplication.model to javafx.base;
    opens com.csc325Team5.payrollapplication to javafx.fxml;
    exports com.csc325Team5.payrollapplication;
    exports com.csc325Team5.payrollapplication.controllers;
    opens com.csc325Team5.payrollapplication.controllers to javafx.fxml;
    exports com.csc325Team5.payrollapplication.controllers.managerViewControllers;
    opens com.csc325Team5.payrollapplication.controllers.managerViewControllers to javafx.fxml;
    exports com.csc325Team5.payrollapplication.controllers.employeeViewControllers;
    opens com.csc325Team5.payrollapplication.controllers.employeeViewControllers to javafx.fxml;
}