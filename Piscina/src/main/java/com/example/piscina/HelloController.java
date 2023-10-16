package com.example.piscina;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Button btnVender;

    @FXML
    private TableColumn<Venta, Integer> colAdultos;

    @FXML
    private TableColumn<Venta, LocalDate> colFecha;

    @FXML
    private TableColumn<Venta, LocalTime> colHora;

    @FXML
    private TableColumn<Venta, Integer> colId;

    @FXML
    private TableColumn<Venta, Integer> colMenores;

    @FXML
    private TableColumn<Venta, Integer> colPrecioAdultos;

    @FXML
    private TableColumn<Venta, Integer> colPrecioMenores;

    @FXML
    private TableView<Venta> tableviewVentas;

    @FXML
    private TextField textfieldAdultos;

    @FXML
    private TextField textfieldMenores;

    RepositorioVentas repositorioVentas;
    Conexion conexion;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        conexion= new Conexion();
        repositorioVentas = new RepositorioVentas(conexion.conexion);

        ObservableList<Venta> listaVentas= repositorioVentas.leerTodos();

        colId.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("id"));
        colAdultos.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("adultos"));
        colMenores.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("menores"));
        colPrecioAdultos.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("precioAdultos"));
        colPrecioMenores.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("precioMenores"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Venta, LocalDate>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<Venta, LocalTime>("hora"));
    }
}
