module com.example.piscina {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.piscina to javafx.fxml;
    exports com.example.piscina;
}