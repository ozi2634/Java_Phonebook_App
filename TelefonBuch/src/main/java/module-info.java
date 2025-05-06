module com.example.telefonbuch {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    //requires com.fasterxml.jackson.care;
    //requires com.fasterxml.jackson.databind;

    opens com.example.telefonbuch to javafx.fxml;
    exports com.example.telefonbuch;
}