package com.company;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/* Ejercicios
    1-Modifica el código para que una fecha se guarde en el fichero fechas.json.
    2-Utiliza la función toArrayJSON para almacenar múltiples fechas en el array.
 */

public class Ej08JSON {
    public static void main(String[] args) {
        LocalDate dNow = LocalDate.now();
        // Ejercicio 1
        escribirFecha(dNow);
        System.out.println(toJson(dNow));
        LocalDate fecha=LocalDate.parse("23-11-2021", DateTimeFormatter.ofPattern("dd-M-yyyy"));
        // Ejercicio 1
        escribirFecha(fecha);
        System.out.println(toJson(fecha));

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-M-yyyy");
        ArrayList<String > fechas=new ArrayList<>();
        fechas.add(toJson(LocalDate.parse("23-11-2021", formato)));
        fechas.add(toJson(LocalDate.parse("03-12-2021", formato)));
        fechas.add(toJson(LocalDate.parse("03-01-2022", formato)));
        // Ejercicio 2
        escribirFechas(fechas);
        System.out.println(toArrayJSON(fechas));

    }

    public static String toJson(LocalDate dNow) {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append("\n");
        sb.append(jsonize("year", dNow.getYear()));
        sb.append(jsonize("month", dNow.getMonth()));
        sb.append(jsonize("day", dNow.getDayOfMonth()));
        sb.append("}").append("\n");
        return sb.toString();
    }

    public static String jsonize(String key, Object value) {
        return String.format("\"%s\": \"%s\",\n", key, value);
    }

    public static String toArrayJSON(ArrayList<String> lista){
        StringBuilder sb=new StringBuilder();
        sb.append("{").append("\n");
        sb.append("[").append("\n");
        for (String cadena:lista) {
            sb.append(cadena).append(", ");
        }
        sb.append("]").append("\n");
        sb.append("}").append("\n");
        return sb.toString();
    }
    // Ejercicio 1
    public static void escribirFecha(LocalDate fecha){
        FileWriter fichero = null;
        PrintWriter escritor = null;

        try {
            fichero = new FileWriter("fechas.json", true);
            escritor = new PrintWriter(fichero);
            escritor.println(toJson(fecha));
            escritor.close();
            fichero.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    // Ejercicio 2
    public static void escribirFechas(ArrayList<String> fechas){
        FileWriter fichero = null;
        PrintWriter escritor = null;

        try {
            fichero = new FileWriter("fechas.json", true);
            escritor = new PrintWriter(fichero);
            escritor.println(toArrayJSON(fechas));
            escritor.close();
            fichero.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
