module com.example.biblioteka {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.naming;


    opens com.example.filmai to javafx.fxml;
    exports com.example.filmai;
    exports com.example.filmai.controller;
    opens com.example.filmai.controller to javafx.fxml;
    exports com.example.filmai.model;
    opens  com.example.filmai.model to javafx.fxml;
}