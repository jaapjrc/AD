package com.example.piscina;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

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

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblTotal;

    RepositorioVentas repositorioVentas;
    Conexion conexion;

    AnimationTimer timer;

    @Override
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

        tableviewVentas.setItems(listaVentas);

        ponReloj();

        textfieldAdultos.setText("0");
        textfieldMenores.setText("0");
    }

    public void pulsarVenta(){
        Venta t=new Venta();
        t.setAdultos(Integer.valueOf(textfieldAdultos.getText()));
        t.setMenores(Integer.valueOf(textfieldMenores.getText()));
        t.setFecha(LocalDate.now());
        t.setHora(LocalTime.now());
        t.setPrecioAdultos(5);
        t.setPrecioMenores(3);


        repositorioVentas.inserta(t);
        actualizarTabla();
    }

    public void total(){
        if (textfieldAdultos.getText().equals("") || textfieldMenores.getText().equals("")) {

        } else {
            int adultos = Integer.valueOf(textfieldAdultos.getText());
            int menores = Integer.valueOf(textfieldMenores.getText());
            int total = adultos * 5 + menores * 3;
            lblTotal.setText(String.valueOf(total));
        }
    }

    public void actualizarTabla(){
        ObservableList<Venta> listaVentas= repositorioVentas.leerTodos();
        tableviewVentas.setItems(listaVentas);
    }

    public void ponReloj(){
        //Pongo un reloj en una etiqueta cuando se pulse la segunda pestaña (tab2) por primera vez
        if(timer==null) {
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    lblFecha.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
            };
            timer.start();
        }
    }
}
