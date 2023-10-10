package com.example.fichaje;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<Trabajador, String> colApellidos;

    @FXML
    private TableColumn<Trabajador, String> colDNI;

    @FXML
    private TableColumn<Trabajador, String> colDepartamento;

    @FXML
    private TableColumn<Trabajador, Integer> colId;

    @FXML
    private TableColumn<Trabajador, String> colNombre;

    @FXML
    private TableView<Trabajador> tblTrabajadores;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtDNI;

    @FXML
    private TextField txtDepartamento;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    private  RepositorioTrabajadores repositorioTrabajadores;

    public void refrescaTabla(){
        RepositorioTrabajadores repositorio = new RepositorioTrabajadores();
        final ObservableList<Trabajador> trabajadores = repositorio.leerTodosFX();
        tblTrabajadores.setItems(trabajadores);
    }

    @FXML
    void delete() {
        RepositorioTrabajadores r = new RepositorioTrabajadores();
        r.delete(Integer.parseInt(txtId.getText()));
        refrescaTabla();
    }

    @FXML
    void inserta() {
        Trabajador alumno = new Trabajador();
        alumno.setNombre(txtNombre.getText());
        alumno.setApellidos(txtApellidos.getText());
        alumno.setDni(txtDNI.getText());
        alumno.setDepartamento(txtDepartamento.getText());

        RepositorioTrabajadores r = new RepositorioTrabajadores();
        r.inserta(alumno);
        refrescaTabla();
    }

    @FXML
    void update() {
        Trabajador trabajador=new Trabajador();
        trabajador.setId(Integer.parseInt(txtId.getText()));
        trabajador.setNombre(txtNombre.getText());
        trabajador.setApellidos(txtApellidos.getText());
        trabajador.setDni(txtDNI.getText());
        trabajador.setDepartamento(txtDepartamento.getText());
        RepositorioTrabajadores r= new RepositorioTrabajadores();
        r.update(trabajador);
        refrescaTabla();
    }

    public void callbackClicTabla(javafx.scene.input.MouseEvent mouseEvent) {

        Trabajador trabajador = (Trabajador) tblTrabajadores.getSelectionModel().getSelectedItem();

        txtId.setText(String.valueOf(trabajador.getId()));
        txtNombre.setText(trabajador.getNombre());
        txtApellidos.setText(trabajador.getApellidos());
        txtDNI.setText(trabajador.getDni());
        txtDepartamento.setText(trabajador.getDepartamento());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repositorioTrabajadores = new RepositorioTrabajadores();
        final ObservableList<Trabajador> trabajadores = repositorioTrabajadores.leerTodosFX();

        colId.setCellValueFactory(new PropertyValueFactory<Trabajador, Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("apellidos"));
        colDNI.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("dni"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("departamento"));

        tblTrabajadores.setItems(trabajadores);
    }
}