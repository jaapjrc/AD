package com.example.todo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioTarea {
    Connection conexion;

    public RepositorioTarea(Connection miConexion) {
        this.conexion = miConexion;
        createTable();
    }

    public void createTable() {
        Statement stmt = null;
        try {
            stmt = conexion.createStatement();
            String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS tareas (" +
                    "id               INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                    "tarea VARCHAR(50)," +
                    "terminada    BOOLEAN);";
            stmt.executeUpdate(CREATE_TABLE_SQL);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public ObservableList<Tarea> leerTodos() {
        ObservableList<Tarea> lista = null;
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM tareas");
            ResultSet rs = ps.executeQuery();
            lista = FXCollections.observableArrayList();
            while (rs.next()) {
                Tarea aux = new Tarea();
                aux.setId(rs.getInt("id"));
                aux.setTarea(rs.getString("tarea"));
                aux.setTerminada(rs.getBoolean("terminada"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public static ObservableList<Tarea> listarTodos(){
        Session s=HibernateUtil.openSession();
        //Mostrar todos los registros de la tabla
        s.beginTransaction();
        ObservableList<Tarea> tareas= (ObservableList<Tarea>) s.createQuery("from tareas").list();

        s.getTransaction().commit();
        s.close();

        return tareas;
    }

    public void inserta(Tarea a){
        PreparedStatement sentencia = null;
        String sentenciaSql = "INSERT INTO tareas (tarea, terminada) VALUES (?, ?)";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, a.getTarea());
            sentencia.setBoolean(2, a.isTerminada());
            sentencia.executeUpdate();

            /*ResultSet rs = sentencia.getGeneratedKeys();
            a.setId(rs.getInt(1));*/

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void insertar(Tarea t){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.save(t);
        s.getTransaction().commit();
        s.close();
        System.out.println("El id es " + t.getId());
    }

    public static void modificar(Tarea t){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.update(t);
        s.getTransaction().commit();
        s.close();
    }

    public void modifica(Tarea a){
        PreparedStatement sentencia = null;
        String sentenciaSql = "UPDATE tareas SET tarea=?, terminada=? WHERE id=?";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, a.getTarea());
            sentencia.setBoolean(2, a.isTerminada());
            sentencia.setInt(3, a.getId());
            sentencia.executeUpdate();

            /*ResultSet rs = sentencia.getGeneratedKeys();
            a.setId(rs.getInt(1));*/

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void eliminar(Tarea t){
        //Eliminar un registro en la tabla
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        //Para borrar solo necesito el id
        s.delete(t);
        s.getTransaction().commit();
        s.close();
    }

}