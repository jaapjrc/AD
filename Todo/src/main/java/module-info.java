module com.example.todo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;


    opens com.example.todo to javafx.fxml, org.hibernate.orm.core;
    exports com.example.todo;
}