module com.example.examenej4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires lombok;


    opens com.example.examenej4 to javafx.fxml;
    exports com.example.examenej4;
}