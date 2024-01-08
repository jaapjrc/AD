package com.example.examenej4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioVentas {
    Connection conexion;

    public RepositorioVentas(Connection miConexion) {
        this.conexion = miConexion;
        createTable();
    }

    public void createTable() {
        Statement stmt = null;
        try {
            stmt = conexion.createStatement();
            String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS ventas (" +
                    "id               INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                    "articulo VARCHAR(50)," +
                    "cantidadVendida INTEGER," +
                    "precio INTEGER," +
                    "importe    DOUBLE);";
            stmt.executeUpdate(CREATE_TABLE_SQL);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public ObservableList<Venta> leerTodos() {
        ObservableList<Venta> lista = null;
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM ventas");
            ResultSet rs = ps.executeQuery();
            lista = FXCollections.observableArrayList();
            while (rs.next()) {
                Venta aux = new Venta();
                aux.setId(rs.getInt("id"));
                aux.setArticulo(rs.getString("articulo"));
                aux.setCantidadVendida(rs.getInt("cantidad_vendida"));
                aux.setPrecio(rs.getDouble("precio"));
                aux.setImporte(rs.getDouble("importe"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void inserta(Venta a){
        PreparedStatement sentencia = null;
        String sentenciaSql = "INSERT INTO ventas (articulo, cantidad_vendida, precio, importe) VALUES (?, ?, ?, ?)";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, a.getArticulo());
            sentencia.setInt(2, a.getCantidadVendida());
            sentencia.setDouble(3, a.getPrecio());
            sentencia.setDouble(4, a.getImporte());
            sentencia.executeUpdate();

            /*ResultSet rs = sentencia.getGeneratedKeys();
            a.setId(rs.getInt(1));*/

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void insertar(Venta t){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.save(t);
        s.getTransaction().commit();
        s.close();
    }

    public static void modificar(Venta t){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.update(t);
        s.getTransaction().commit();
        s.close();
    }

    public void modifica(Venta a){
        PreparedStatement sentencia = null;
        String sentenciaSql = "UPDATE ventas SET articulo=?, cantidad_vendida=?, precio=?, importe=? WHERE id=?";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, a.getArticulo());
            sentencia.setInt(2, a.getCantidadVendida());
            sentencia.setDouble(3, a.getPrecio());
            sentencia.setDouble(4, a.getImporte());
            sentencia.setInt(5, a.getId());
            sentencia.executeUpdate();

            /*ResultSet rs = sentencia.getGeneratedKeys();
            a.setId(rs.getInt(1));*/

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void eliminar(Venta t){
        //Eliminar un registro en la tabla
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        //Para borrar solo necesito el id
        s.delete(t);
        s.getTransaction().commit();
        s.close();
    }

}