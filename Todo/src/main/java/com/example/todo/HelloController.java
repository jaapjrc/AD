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

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnInsertarModificar;

    @FXML
    private CheckBox checkboxTerminada;

    @FXML
    private TableColumn<Tarea, Integer> colID;

    @FXML
    private TableColumn<Tarea, String> colTarea;

    @FXML
    private TableColumn<Tarea, Boolean> colTerminada;

    @FXML
    private TableView<Tarea> tableviewTareas;

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

        ObservableList<Tarea> listaTareas= repositorioTarea.listarTodos();

        colID.setCellValueFactory(new PropertyValueFactory<Tarea, Integer>("id"));
        colTarea.setCellValueFactory(new PropertyValueFactory<Tarea, String>("tarea"));
        colTerminada.setCellValueFactory(new PropertyValueFactory<Tarea, Boolean>("terminada"));

        tableviewTareas.setItems(listaTareas);
    }

    public void actualizarTabla() {
        ObservableList<Tarea> listaTareas = repositorioTarea.leerTodos();
        tableviewTareas.setItems(listaTareas);
    }

    public void pulsarInsertarModificar(){
        ObservableList<Tarea> listaTareas = repositorioTarea.leerTodos();
        if (textfieldID.getText().isEmpty()){
            Tarea t = new Tarea();
            t.setTarea(textfieldTarea.getText());
            if (checkboxTerminada.isSelected()){
                t.setTerminada(true);
            } else {
                t.setTerminada(false);
            }
            repositorioTarea.inserta(t);
            actualizarTabla();
        } else {
            for (int i=0; i < listaTareas.toArray().length; i++){
                if (Integer.valueOf(textfieldID.getText()).equals(listaTareas.get(i).getId())){
                    Tarea t=new Tarea();
                    t.setId(Integer.parseInt(textfieldID.getText()));
                    t.setTarea(textfieldTarea.getText());
                    if (checkboxTerminada.isSelected()){
                        t.setTerminada(true);
                    } else {
                        t.setTerminada(false);
                    }

                    repositorioTarea.modifica(t);
                    actualizarTabla();
                }
            }
        }
    }
}
