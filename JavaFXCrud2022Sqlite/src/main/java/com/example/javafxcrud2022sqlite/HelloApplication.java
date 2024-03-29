package com.example.javafxcrud2022sqlite;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 540);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        /*RepositorioAlumnos r=new RepositorioAlumnos();
        Alumno a=new Alumno();
        r.inserta(a);
        ArrayList<Alumno> lista=r.leerTodos();
        for(Alumno aux:lista){
            System.out.println(aux.toString());
        }*/
        launch();
    }
}