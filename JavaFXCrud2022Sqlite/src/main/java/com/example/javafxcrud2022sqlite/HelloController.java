package com.example.javafxcrud2022sqlite;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<Alumno, String> colApellidos;

    @FXML
    private TableColumn<Alumno, String> colDNI;

    @FXML
    private TableColumn<Alumno, LocalDate> colFechaNacimiento;

    @FXML
    private TableColumn<Alumno, Integer> colId;

    @FXML
    private TableColumn<Alumno, String> colNombre;

    @FXML
    private TableView<Alumno> tblAlumnos;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtDNI;

    @FXML
    private TextField txtFechaNacimiento;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RepositorioAlumnos repositorio=new RepositorioAlumnos();

        final ObservableList<Alumno> alumnos = repositorio.leerTodosFX();

        colId.setCellValueFactory(new PropertyValueFactory<Alumno,Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apellidos"));
        colDNI.setCellValueFactory(new PropertyValueFactory<Alumno,String>("dni"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<Alumno,LocalDate>("fechaNacimiento"));

        tblAlumnos.setItems(alumnos);
    }

    public void refrescaTabla(){
        RepositorioAlumnos repositorio = new RepositorioAlumnos();
        final ObservableList<Alumno> alumnos = repositorio.leerTodosFX();
        tblAlumnos.setItems(alumnos);
    }

    public void insertar(){
        Alumno alumno = new Alumno();
        alumno.setNombre(txtNombre.getText());
        alumno.setApellidos(txtApellidos.getText());
        alumno.setDni(txtDNI.getText());
        alumno.setFechaNacimiento(LocalDate.parse(txtFechaNacimiento.getText()));

        RepositorioAlumnos r = new RepositorioAlumnos();
        r.inserta(alumno);
        refrescaTabla();
    }

    public void modificar(){
        Alumno alumno=new Alumno();
        alumno.setId(Integer.parseInt(txtId.getText()));
        alumno.setNombre(txtNombre.getText());
        alumno.setApellidos(txtApellidos.getText());
        alumno.setDni(txtDNI.getText());
        alumno.setFechaNacimiento(LocalDate.parse(txtFechaNacimiento.getText()));
        RepositorioAlumnos r= new RepositorioAlumnos();
        r.update(alumno);
        refrescaTabla();
    }

    public void borrar(){
        RepositorioAlumnos r = new RepositorioAlumnos();
        r.delete(Integer.parseInt(txtId.getText()));
        refrescaTabla();
    }

    public void callbackClicTabla(javafx.scene.input.MouseEvent mouseEvent) {

        Alumno alumno = (Alumno) tblAlumnos.getSelectionModel().getSelectedItem();

        txtId.setText(String.valueOf(alumno.getId()));
        txtNombre.setText(alumno.getNombre());
        txtApellidos.setText(alumno.getApellidos());
        txtDNI.setText(alumno.getDni());
        txtFechaNacimiento.setText(alumno.getFechaNacimiento().toString());

    }
}