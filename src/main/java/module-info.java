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

    opens com.example.payrollapplication.model to javafx.base;
    opens com.example.payrollapplication to javafx.fxml;
    exports com.example.payrollapplication;
    exports com.example.payrollapplication.controllers;
    opens com.example.payrollapplication.controllers to javafx.fxml;
    exports com.example.payrollapplication.controllers.managerViewControllers;
    opens com.example.payrollapplication.controllers.managerViewControllers to javafx.fxml;
    exports com.example.payrollapplication.controllers.employeeViewControllers;
    opens com.example.payrollapplication.controllers.employeeViewControllers to javafx.fxml;
}