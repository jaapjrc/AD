package com.example.piscina;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class RepositorioVentas {
    Connection conexion;

    public RepositorioVentas(Connection miConexion) {
        this.conexion = miConexion;
        createTable();
    }

    public void createTable(){
        Statement stmt=null;
        try {
            stmt = conexion.createStatement();
            String CREATE_TABLE_SQL="CREATE TABLE IF NOT EXISTS ventas (" +
                    "id               INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                    "adultos    INTEGER," +
                    "menores    INTEGER," +
                    "fecha     DATE," +
                    "hora     TIME," +
                    "precio_adultos    INTEGER," +
                    "precio_menores    INTEGER);";
            stmt.executeUpdate(CREATE_TABLE_SQL);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public ObservableList<Venta> leerTodos(){
        ObservableList<Venta> lista=null;
        try {
            PreparedStatement ps=conexion.prepareStatement("SELECT * FROM ventas");
            ResultSet rs=ps.executeQuery();
            lista= FXCollections.observableArrayList();
            while(rs.next()){
                Venta aux=new Venta();
                aux.setId(rs.getInt("id"));
                aux.setAdultos(rs.getInt("adultos"));
                aux.setMenores(rs.getInt("menores"));
                aux.setFecha(rs.getDate("fecha").toLocalDate());
                aux.setHora(rs.getTime("hora").toLocalTime());
                aux.setPrecioAdultos(rs.getInt("precio_adultos"));
                aux.setPrecioMenores(rs.getInt("precio_menores"));
                lista.add(aux);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void inserta(Venta a){
        PreparedStatement sentencia = null;
        String sentenciaSql = "INSERT INTO ventas (adultos, menores, fecha, hora, precio_adultos, precio_menores) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, a.getAdultos());
            sentencia.setInt(2, a.getMenores());
            sentencia.setDate(3, Date.valueOf(a.getFecha()));
            sentencia.setTime(4, Time.valueOf(a.getHora()));
            sentencia.setInt(5, a.getPrecioAdultos());
            sentencia.setInt(6, a.getPrecioMenores());
            sentencia.executeUpdate();

            /*ResultSet rs = sentencia.getGeneratedKeys();
            a.setId(rs.getInt(1));*/

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void modificar(Venta a){
        PreparedStatement sentencia = null;

        String sentenciaSQL = "UPDATE ventas SET adultos=?, menores=?, fecha=?, hora=?, precio_adultos=?, precio_menores=? WHERE id = ?";

        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setInt(1, a.getAdultos());
            sentencia.setInt(2, a.getMenores());
            sentencia.setDate(3, Date.valueOf(a.getFecha()));
            sentencia.setTime(4, Time.valueOf(a.getHora()));
            sentencia.setInt(5, a.getPrecioAdultos());
            sentencia.setInt(6, a.getPrecioMenores());
            sentencia.setInt(7, a.getId());
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        PreparedStatement sentencia = null;
        String sentenciaSql = "DELETE FROM ventas WHERE id=?";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);

            sentencia.setInt(1, id);

            sentencia.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

}
