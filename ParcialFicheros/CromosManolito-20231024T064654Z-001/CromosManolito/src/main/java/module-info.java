module com.example.cromosmanolito {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.cromosmanolito to javafx.fxml;
    exports com.example.cromosmanolito;
}