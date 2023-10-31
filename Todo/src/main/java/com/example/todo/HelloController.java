package com.example.todo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnInsertarModificar;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnEliminar;

    @FXML
    private CheckBox checkboxTerminada;

    @FXML
    private TableColumn<Tarea, Integer> colID;

    @FXML
    private TableColumn<Tarea, String> colTarea;

    @FXML
    private TableColumn<Tarea, Boolean> colTerminada;

    @FXML
    private TableColumn<Tarea, Integer> colID1;

    @FXML
    private TableColumn<Tarea, String> colTarea1;

    @FXML
    private TableColumn<Tarea, Boolean> colTerminada1;

    @FXML
    private TableView<Tarea> tableviewTareasIncompletas;

    @FXML
    private TableView<Tarea> tableviewTareasCompletas;

    @FXML
    private TextField textfieldID;

    @FXML
    private TextField textfieldTarea;

    Conexion conexion;

    RepositorioTarea repositorioTarea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        conexion= new Conexion();
//        repositorioTarea = new RepositorioTarea(conexion.conexion);
        HibernateUtil.inicia();

//        ObservableList<Tarea> listaTareas = repositorioTarea.leerTodos();
        ObservableList<Tarea> listaTareas= repositorioTarea.listarTodosFalse();
        ObservableList<Tarea> listaTareas1= repositorioTarea.listarTodosTrue();

        colID.setCellValueFactory(new PropertyValueFactory<Tarea, Integer>("id"));
        colTarea.setCellValueFactory(new PropertyValueFactory<Tarea, String>("tarea"));
        colTerminada.setCellValueFactory(new PropertyValueFactory<Tarea, Boolean>("terminada"));

        tableviewTareasIncompletas.setItems(listaTareas);

        colID1.setCellValueFactory(new PropertyValueFactory<Tarea, Integer>("id"));
        colTarea1.setCellValueFactory(new PropertyValueFactory<Tarea, String>("tarea"));
        colTerminada1.setCellValueFactory(new PropertyValueFactory<Tarea, Boolean>("terminada"));

        tableviewTareasCompletas.setItems(listaTareas1);
    }

    public void actualizarTabla() {
        ObservableList<Tarea> listaTareas = repositorioTarea.leerTodos();
        tableviewTareasIncompletas.setItems(listaTareas);
    }

    public void actualizarTablaIncompletas() {
        ObservableList<Tarea> listaTareas = repositorioTarea.listarTodosFalse();
        tableviewTareasIncompletas.setItems(listaTareas);
    }

    public void actualizarTablaCompletas() {
        ObservableList<Tarea> listaTareas = repositorioTarea.listarTodosTrue();
        tableviewTareasIncompletas.setItems(listaTareas);
    }

    public void insertar(){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        Tarea t = new Tarea();
        t.setTarea(textfieldTarea.getText());
        if (checkboxTerminada.isSelected()){
            t.setTerminada(true);
        } else {
            t.setTerminada(false);
        }
        s.save(t);
        s.getTransaction().commit();
        s.close();
        actualizarTablaIncompletas();
        actualizarTablaCompletas();
    }

    public void modificar(){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        Tarea t=new Tarea();
        t.setId(Integer.parseInt(textfieldID.getText()));
        t.setTarea(textfieldTarea.getText());
        if (checkboxTerminada.isSelected()){
            t.setTerminada(true);
        } else {
            t.setTerminada(false);
        }
        s.update(t);
        s.getTransaction().commit();
        s.close();
        actualizarTablaIncompletas();
        actualizarTablaCompletas();
    }

    public void eliminar(){
        //Eliminar un registro en la tabla
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        Tarea t = new Tarea();
        t.setId(Integer.parseInt(textfieldID.getText()));
        s.delete(t);
        s.getTransaction().commit();
        s.close();
        actualizarTablaIncompletas();
        actualizarTablaCompletas();
    }

//    public void pulsarInsertarModificar(){
//        ObservableList<Tarea> listaTareas = repositorioTarea.leerTodos();
//        if (textfieldID.getText().isEmpty()){
//            Tarea t = new Tarea();
//            t.setTarea(textfieldTarea.getText());
//            if (checkboxTerminada.isSelected()){
//                t.setTerminada(true);
//            } else {
//                t.setTerminada(false);
//            }
//            repositorioTarea.inserta(t);
//            actualizarTabla();
//        } else {
//            for (int i=0; i < listaTareas.toArray().length; i++){
//                if (Integer.valueOf(textfieldID.getText()).equals(listaTareas.get(i).getId())){
//                    Tarea t=new Tarea();
//                    t.setId(Integer.parseInt(textfieldID.getText()));
//                    t.setTarea(textfieldTarea.getText());
//                    if (checkboxTerminada.isSelected()){
//                        t.setTerminada(true);
//                    } else {
//                        t.setTerminada(false);
//                    }
//
//                    repositorioTarea.modifica(t);
//                    actualizarTabla();
//                }
//            }
//        }
//    }
}
