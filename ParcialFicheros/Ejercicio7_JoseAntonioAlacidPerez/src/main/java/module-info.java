module com.example.ejercicio7_joseantonioalacidperez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;


    opens com.example.ejercicio7_joseantonioalacidperez to javafx.fxml;
    exports com.example.ejercicio7_joseantonioalacidperez;
}