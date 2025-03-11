module dev.phil.poobdatv7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.phil.poobdatv7 to javafx.fxml;
    exports dev.phil.poobdatv7;
}