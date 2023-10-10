package com.example.fichaje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection conexion;

    public Conexion(){
        setConexion();
    }

    public void setConexion(){
        try {
            // db parameters
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/fichaje?createDatabaseIfNotExist=true","root","");

            System.out.println("Connection to MySql has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
