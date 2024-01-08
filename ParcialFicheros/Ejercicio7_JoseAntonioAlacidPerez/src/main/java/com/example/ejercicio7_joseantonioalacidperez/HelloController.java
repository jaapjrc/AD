package com.example.ejercicio7_joseantonioalacidperez;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableColumn<Sala, Integer> colCapacidad;

    @FXML
    private TableColumn<Sala, Integer> colID;

    @FXML
    private TableColumn<Sala, String> colNombre;

    @FXML
    private TableColumn<Sala, Integer> colPrecio;

    @FXML
    private TableColumn<Sala, String> colTipo;

    @FXML
    private ComboBox<String> comboTipo;

    @FXML
    private TableView<Sala> tableSalas;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    RepositorioSalas repositorioSalas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        HibernateUtil.inicia();

        ObservableList<Sala> listaSalas= repositorioSalas.listarTodos();

        colID.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Sala, String>("nombre"));
        colTipo.setCellValueFactory(new PropertyValueFactory<Sala,String>("tipo"));
        colCapacidad.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("capacidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("precio"));

        ObservableList<String> listaTipos = FXCollections.observableArrayList("Jacuzzi", "Piscina", "Ducha");
        comboTipo.setItems(listaTipos);
        comboTipo.getSelectionModel().selectFirst();

        tableSalas.setItems(listaSalas);
    }

    public void actualizarTabla(){
        ObservableList<Sala> listaSalas= repositorioSalas.listarTodos();
        tableSalas.setItems(listaSalas);
    }

    public void pulsarInsertar(){
        Sala sala = new Sala();
        sala.setNombre(txtNombre.getText());
        sala.setTipo(comboTipo.getSelectionModel().getSelectedItem().toString());
        sala.setCapacidad(Integer.valueOf(txtCapacidad.getText()));
        sala.setPrecio(Integer.valueOf(txtPrecio.getText()));

        repositorioSalas.insertar(sala);
        actualizarTabla();
    }

    public void pulsarModificar(){
        Sala sala = new Sala();
        sala.setId(Integer.valueOf(txtID.getText()));
        sala.setNombre(txtNombre.getText());
        sala.setTipo(comboTipo.getSelectionModel().getSelectedItem().toString());
        sala.setCapacidad(Integer.valueOf(txtCapacidad.getText()));
        sala.setPrecio(Integer.valueOf(txtPrecio.getText()));

        repositorioSalas.modificar(sala);
        actualizarTabla();
    }

    public void pulsarEliminar(){
        Sala sala = new Sala();
        sala.setId(Integer.valueOf(txtID.getText()));

        repositorioSalas.eliminar(sala);
        actualizarTabla();
    }

}