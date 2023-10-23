module com.example.todo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens com.example.todo to javafx.fxml;
    exports com.example.todo;
}