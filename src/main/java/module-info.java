module dev.phil.poobdatv7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens dev.phil.poobdatv7 to javafx.fxml;
    opens dev.phil.poobdatv7.controller to javafx.fxml;
    opens dev.phil.poobdatv7.view to javafx.fxml;

    exports dev.phil.poobdatv7;
    exports dev.phil.poobdatv7.controller;
    exports dev.phil.poobdatv7.model;
    exports dev.phil.poobdatv7.dao;
    exports dev.phil.poobdatv7.dto;
    exports dev.phil.poobdatv7.view;
}