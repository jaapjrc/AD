package com.example.fichaje;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField apellidosTextField;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<Trabajador, String> colApellidos;

    @FXML
    private TableColumn<Trabajador, String> colDepartamento;

    @FXML
    private TableColumn<Trabajador, String> colDni;

    @FXML
    private TableColumn<Trabajador, Integer> colId;

    @FXML
    private TableColumn<Trabajador, String> colNombre;
    @FXML
    private TextField idTextField;

    @FXML
    private TextField dniTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TableView<Trabajador> trabajadorTable;
    @FXML
    private TableView<Trabajador> trabajadorTable1;
    @FXML
    private TableColumn<Trabajador, String> colApellidos1;

    @FXML
    private TableColumn<Trabajador, String> colDepartamento1;

    @FXML
    private TableColumn<Trabajador, String> colDni1;

    @FXML
    private TableColumn<Trabajador, Integer> colId1;

    @FXML
    private TableColumn<Trabajador, String> colNombre1;
    @FXML
    private ComboBox<String> comboDepartamento;
    @FXML
    private Label tiempo;
    @FXML
    private Tab tab2;

    @FXML
    private TableColumn<Fichaje, Date> colFechaEntrada;

    @FXML
    private TableColumn<Fichaje, Date> colFechaSalida;

    @FXML
    private TableColumn<Fichaje, Time> colHoraEntrada;

    @FXML
    private TableColumn<Fichaje, Time> colHoraSalida;

    @FXML
    private TableColumn<Fichaje, Integer> colIDFichaje;

    @FXML
    private TableColumn<Fichaje, Integer> colIDTrabajador;

    //@FXML
    //private TableColumn<Fichaje, Boolean> colSalidaFijada;

    @FXML
    private TableView<Fichaje> fichajeTable;

    @FXML
    private Tab tab3;

    @FXML
    private TableView<Fichaje> tableCrudFichaje;

    @FXML
    private TableColumn<Fichaje, Date> colFechaEntrada2;

    @FXML
    private TableColumn<Fichaje, Date> colFechaSalida2;

    @FXML
    private TableColumn<Fichaje, Time> colHoraEntrada2;

    @FXML
    private TableColumn<Fichaje, Time> colHoraSalida2;

    @FXML
    private TableColumn<Fichaje, Integer> colId2;

    @FXML
    private TableColumn<Fichaje, Integer> colIDTrabajador2;

    @FXML
    private TableColumn<Fichaje, Boolean> colSalidaFijada;

    @FXML
    private TextField fechaEntradaTextField;

    @FXML
    private TextField fechaSalidaTextField;

    @FXML
    private TextField fijada;

    @FXML
    private TextField horaEntradaTextField;

    @FXML
    private TextField horaSalidaTextField;

    @FXML
    private TextField idFichajeTextField;

    @FXML
    private TextField idTrabajadorFichajeTextField;

    @FXML
    private Button btnDeleteFichaje;


    AnimationTimer timer;

    RepositorioTrabajador repositorioTrabajador;
    RepositorioFichaje repositorioFichaje;
    Conexion conexion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conexion=new Conexion();
        repositorioTrabajador=new RepositorioTrabajador(conexion.conexion);

        ObservableList<Trabajador> listaTrabajadores=repositorioTrabajador.leerTodosFX();

        colId.setCellValueFactory(new PropertyValueFactory<Trabajador, Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("apellidos"));
        colDni.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("dni"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("departamento"));

        ObservableList<String> listaDepartamentos = FXCollections.observableArrayList("Administración", "Producción", "Limpieza");
        comboDepartamento.setItems(listaDepartamentos);
        comboDepartamento.getSelectionModel().selectFirst();

        trabajadorTable.setItems(listaTrabajadores);

        trabajadorTable.setOnMouseClicked(e -> {
            Trabajador t =trabajadorTable.getSelectionModel().getSelectedItem();
            idTextField.setText(String.valueOf(t.getId()));
            nombreTextField.setText(t.getNombre());
            apellidosTextField.setText(t.getApellidos());
            dniTextField.setText(t.getDni());
            //departamentoTextField.setText(t.getDepartamento());
            comboDepartamento.getSelectionModel().select(t.getDepartamento());
        });

        //Ahora preparo la tabla de los trabajadores de la segunda pestaña
        colId1.setCellValueFactory(new PropertyValueFactory<Trabajador, Integer>("id"));
        colNombre1.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("nombre"));
        colApellidos1.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("apellidos"));
        colDni1.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("dni"));
        colDepartamento1.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("departamento"));

        trabajadorTable1.setItems(listaTrabajadores);

        //Ahora creo el repositorio para añadir fichajes usando la conexión anterior a la BBDD
        repositorioFichaje=new RepositorioFichaje(conexion.conexion);

        ObservableList<Fichaje> listaFichajes=repositorioFichaje.leerTodosFX();

        colIDFichaje.setCellValueFactory(new PropertyValueFactory<Fichaje, Integer>("id"));
        colIDTrabajador.setCellValueFactory(new PropertyValueFactory<Fichaje, Integer>("idTrabajador"));
        colFechaEntrada.setCellValueFactory(new PropertyValueFactory<Fichaje, Date>("fechaEntrada"));
        colHoraEntrada.setCellValueFactory(new PropertyValueFactory<Fichaje, Time>("horaEntrada"));
        colFechaSalida.setCellValueFactory(new PropertyValueFactory<Fichaje, Date>("fechaSalida"));
        colHoraSalida.setCellValueFactory(new PropertyValueFactory<Fichaje, Time>("horaSalida"));
        //colSalidaFijada.setCellValueFactory(new PropertyValueFactory<Fichaje, Boolean>("salida_fijada"));

        fichajeTable.setItems(listaFichajes);

        colId2.setCellValueFactory(new PropertyValueFactory<Fichaje, Integer>("id"));
        colIDTrabajador2.setCellValueFactory(new PropertyValueFactory<Fichaje, Integer>("idTrabajador"));
        colFechaEntrada2.setCellValueFactory(new PropertyValueFactory<Fichaje, Date>("fechaEntrada"));
        colHoraEntrada2.setCellValueFactory(new PropertyValueFactory<Fichaje, Time>("horaEntrada"));
        colFechaSalida2.setCellValueFactory(new PropertyValueFactory<Fichaje, Date>("fechaSalida"));
        colHoraSalida2.setCellValueFactory(new PropertyValueFactory<Fichaje, Time>("horaSalida"));
        colSalidaFijada.setCellValueFactory(new PropertyValueFactory<Fichaje, Boolean>("salidaFijada"));

        tableCrudFichaje.setItems(listaFichajes);

        tableCrudFichaje.setOnMouseClicked(e -> {
            Fichaje t =tableCrudFichaje.getSelectionModel().getSelectedItem();
            idFichajeTextField.setText(String.valueOf(t.getId()));
            idTrabajadorFichajeTextField.setText(String.valueOf(t.getIdTrabajador()));
            fechaEntradaTextField.setText(String.valueOf(t.getFechaEntrada()));
            horaEntradaTextField.setText(String.valueOf(t.getHoraEntrada()));
            fechaSalidaTextField.setText(String.valueOf(t.getFechaSalida()));
            horaSalidaTextField.setText(String.valueOf(t.getHoraSalida()));
            fijada.setText(String.valueOf(t.isSalidaFijada()));
        });

    }

    public void actualizarTabla(){
        ObservableList<Trabajador> listaTrabajadores=repositorioTrabajador.leerTodosFX();
        trabajadorTable.setItems(listaTrabajadores);
        trabajadorTable1.setItems(listaTrabajadores);
        ObservableList<Fichaje> listaFichajes=repositorioFichaje.leerTodosFX();
        fichajeTable.setItems(listaFichajes);
        tableCrudFichaje.setItems(listaFichajes);

    }
    public void pulsarInsertar(){
        Trabajador t=new Trabajador();
        t.setNombre(nombreTextField.getText());
        t.setApellidos(apellidosTextField.getText());
        t.setDni(dniTextField.getText());
        //t.setDepartamento(departamentoTextField.getText());
        t.setDepartamento(comboDepartamento.getSelectionModel().getSelectedItem().toString());

        repositorioTrabajador.inserta(t);
        actualizarTabla();
    }

    public void pulsarModificar(){
        Trabajador t=new Trabajador();
        t.setId(Integer.parseInt(idTextField.getText()));
        t.setNombre(nombreTextField.getText());
        t.setApellidos(apellidosTextField.getText());
        t.setDni(dniTextField.getText());
        //t.setDepartamento(departamentoTextField.getText());

        repositorioTrabajador.modificar(t);
        actualizarTabla();
    }

    public void pulsarBorrar(){
        RepositorioTrabajador r = new RepositorioTrabajador(conexion.conexion);
        r.delete(Integer.parseInt(idTextField.getText()));
        actualizarTabla();
    }

    public void ponReloj(){
        //Pongo un reloj en una etiqueta cuando se pulse la segunda pestaña (tab2) por primera vez
        if(timer==null) {
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    tiempo.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
            };
            timer.start();
        }
    }

    public void pulsaFichaje(){
        //Cogemos el id del trabajador seleccionado en la tabla
        Trabajador t =trabajadorTable1.getSelectionModel().getSelectedItem();
        if(t != null) {
            int idTrabajadorActual = t.getId();

            //Consultamos si hay alguna entrada sin salida de ese trabajador. Si no hay fichajes o todos tienen salida, devuelve null
            Fichaje fichaje = repositorioFichaje.fichajeSinSalida(idTrabajadorActual);

            //Si fichaje es null, tengo que hacer un nuevo fichaje con los datos de entrada
            if (fichaje == null) {
                fichaje = new Fichaje();
                fichaje.setIdTrabajador(idTrabajadorActual);
                fichaje.setFechaEntrada(java.sql.Date.valueOf(LocalDate.now()));
                fichaje.setHoraEntrada(java.sql.Time.valueOf(LocalTime.now()));
                fichaje.setSalidaFijada(false);
                repositorioFichaje.inserta(fichaje);
                actualizarTabla();
            } else {
                //Si nos devuelven un fichaje, completamos los datos de la fecha y hora de salida
                fichaje.setFechaSalida(java.sql.Date.valueOf(LocalDate.now()));
                fichaje.setHoraSalida(java.sql.Time.valueOf(LocalTime.now()));
                fichaje.setSalidaFijada(true);
                repositorioFichaje.modifica(fichaje);
                actualizarTabla();
            }
        }
    }

    public void pulsarBorrarFichaje(){
        RepositorioFichaje r = new RepositorioFichaje(conexion.conexion);
        r.delete(Integer.parseInt(idFichajeTextField.getText()));
        actualizarTabla();
    }

}
