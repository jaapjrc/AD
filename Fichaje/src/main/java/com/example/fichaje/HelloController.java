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