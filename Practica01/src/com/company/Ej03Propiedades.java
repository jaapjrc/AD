package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/*
    Ejercicios:
    1-Modifica escribirPropiedades para que se guarden otras propiedades con tus apellidos y tu edad
    2-Cambia leerPropiedades para que se muestren las nuevas propiedades que has creado
    3-Crea un programa que pregunte al usuario su nombre y lo guarde en un archivo de propiedades
    4-Modifica el programa anterior para que solo pregunte el nombre del usuario si no se ha creado ya el archivo.
    Puedes utilizar este código para comprobar si ya existe el archivo
    File file1 = new File("sampleFile.txt");
    if(file1.exists() && !file1.isDirectory()){
        System.out.println(file1 + " Exists");
    }
 */

public class Ej03Propiedades {
    public static void main(String[] args) {
        // Ejercicio 1
        escribirPropiedades();
        // Ejercicio 2
        leerPropiedades();
        // Ejercicio 3
        escribirPropiedadesUsuario();
        // Ejercicio 4
        escribirPropiedadesUsuario1();
    }

    public static void escribirPropiedadesUsuario() {
        Properties configuracion = new Properties();
        String miUsuario = JOptionPane.showInputDialog("Introduce tu nombre");
            configuracion.setProperty("user", miUsuario);
            try {
                configuracion.store(new FileOutputStream(miUsuario + ".props"), "Fichero de configuracion");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }


    public static void escribirPropiedadesUsuario1() {
        Properties configuracion = new Properties();
        String miUsuario = JOptionPane.showInputDialog("Introduce tu nombre");
        File file1 = new File(miUsuario + ".props");
        if(file1.exists() && !file1.isDirectory()){
            System.out.println(file1 + " Exists");
        } else {
            configuracion.setProperty("user", miUsuario);
            try {
                configuracion.store(new FileOutputStream(miUsuario + ".props"), "Fichero de configuracion");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static void escribirPropiedades() {
        Properties configuracion = new Properties();
        String miUsuario = "Yo", miContrasena = "xxx", apellidos = "Alacid Pérez", edad = "21";
        configuracion.setProperty("user", miUsuario);
        configuracion.setProperty("password", miContrasena);
        configuracion.setProperty("apellidos", apellidos);
        configuracion.setProperty("edad", edad);
        try {
            configuracion.store(new FileOutputStream("configuracion.props"), "Fichero de configuracion");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

   /*
   Original
   public static void escribirPropiedades(){
        Properties configuracion = new Properties();
        String miUsuario="Yo", miContrasena="xxx";
        configuracion.setProperty("user", miUsuario);
        configuracion.setProperty("password", miContrasena);
        try {
            configuracion.store(new FileOutputStream("configuracion.props"),"Fichero de configuracion");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }*/

    public static void leerPropiedades() {
        Properties configuracion = new Properties();
        String usuario, password, apellidos, edad;
        try {
            configuracion.load(new FileInputStream("configuracion.props"));
            usuario = configuracion.getProperty("user");
            password = configuracion.getProperty("password");
            apellidos = configuracion.getProperty("apellidos");
            edad = configuracion.getProperty("edad");
            System.out.println(usuario + ", " + password + ", " + apellidos + ", " + edad);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

  /*
  Original
  public static void leerPropiedades(){
        Properties configuracion = new Properties();
        String usuario, password;
        try {
            configuracion.load(new FileInputStream("configuracion.props"));
            usuario = configuracion.getProperty("user");
            password = configuracion.getProperty("password");
            System.out.println(usuario + ", " + password);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }*/

}
