package com.example.fichaje;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioTrabajadores {
    Connection conexion;
    public RepositorioTrabajadores(){
        setConexion();
        createTable();
    }
    public void setConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/fichaje?createDatabaseIfNotExist=true", "root", "");

            System.out.println("Connection to MySQL has been established.");
            createTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void createTable(){
        Statement stmt=null;
        try {
            stmt = conexion.createStatement();
            String CREATE_TABLE_SQL="CREATE TABLE IF NOT EXISTS trabajadores (" +
                    "    id               INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    nombre           VARCHAR (50), apellidos VARCHAR(50), dni varchar(15), " +
                    "    departamento     VARCHAR (50)" +
                    ");";
            stmt.executeUpdate(CREATE_TABLE_SQL);
            Trabajador t = new Trabajador();
            t.setNombre("Antonio");
            t.setApellidos("Salinas");
            t.setDepartamento("Limpieza");
            t.setDni("Nooslodigo");
            inserta(t);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public ArrayList<Trabajador> leerTodos(){
        ArrayList<Trabajador> lista=null;
        try {
            PreparedStatement ps=conexion.prepareStatement("SELECT * FROM trabajadores");
            ResultSet rs=ps.executeQuery();
            lista=new ArrayList<>();
            while(rs.next()){
                Trabajador aux=new Trabajador();
                aux.setId(rs.getInt("id"));
                aux.setDni(rs.getString("dni"));
                aux.setNombre(rs.getString("nombre"));
                aux.setApellidos(rs.getString("apellidos"));
                aux.setDepartamento(rs.getString("departamento"));
                lista.add(aux);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public ObservableList<Trabajador> leerTodosFX(){
        ObservableList<Trabajador> lista=null;
        try {
            PreparedStatement ps=conexion.prepareStatement("SELECT * FROM trabajadores");
            ResultSet rs=ps.executeQuery();
            lista= FXCollections.observableArrayList();
            while(rs.next()){
                Trabajador aux=new Trabajador();
                aux.setId(rs.getInt("id"));
                aux.setDni(rs.getString("dni"));
                aux.setNombre(rs.getString("nombre"));
                aux.setApellidos(rs.getString("apellidos"));
                aux.setDepartamento(rs.getString("departamento"));
                lista.add(aux);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public void inserta(Trabajador a){
        PreparedStatement sentencia = null;
        String sentenciaSql = "INSERT INTO trabajadores (nombre, apellidos, dni, departamento) VALUES (?, ?, ?, ?)";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, a.getNombre());
            sentencia.setString(2, a.getApellidos());
            sentencia.setString(3, a.getDni());
            //sentencia.setString(4, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(LocalDate.now()));
            sentencia.setString(4, a.getDepartamento());
            sentencia.executeUpdate();

            /*ResultSet rs = sentencia.getGeneratedKeys();
            a.setId(rs.getInt(1));*/

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void update(Trabajador a){
        PreparedStatement sentencia = null;
        String sentenciaSql = "UPDATE trabajadores SET nombre=?, apellidos=?, dni=?, departamento=? WHERE id=?";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, a.getNombre());
            sentencia.setString(2, a.getApellidos());
            sentencia.setString(3, a.getDni());
            //sentencia.setString(4, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(LocalDate.now()));
            sentencia.setString(4, a.getDepartamento());
            sentencia.setInt(5, a.getId());
            sentencia.executeUpdate();

            ResultSet rs = sentencia.getGeneratedKeys();
            a.setId(rs.getInt(1));

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void delete(int id){
        PreparedStatement sentencia = null;
        String sentenciaSql = "DELETE FROM trabajadores WHERE id=?";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);

           sentencia.setInt(1, id);

           sentencia.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}

