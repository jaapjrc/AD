package com.company;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*Ejercicios
    1-Modifica la función escribirFichero para que guarde en un archivo con el nombre "fichero01.txt" y que escriba tu nombre al final del fichero
    2-Modifica escribirFichero para que se escriban varias líneas en el fichero
    3-Modifica escribirFichero para que se escriban 100 líneas
    4-Comprueba lo que ocurre si cambias false por true en la línea fichero = new FileWriter("archivo.txt", false);
    4-Modifica la función escribirFichero para que lo que se escriba no borre el contenido anterior
    5-Modifica la función escribirFichero para que se le pueda pasar como parámetro el nombre del fichero a guardar.
    6-Añade otro parámetro en el que se indique el texto que se quiere escribir en el fichero
    7-(Difícil) Crea una nueva función combinando las anteriores para que se copie el archivo en otro archivo diferente y que añada una línea diciendo "Este archivo es una copia"
    8-Utiliza la función copiarFichero para copiar archivo.txt en micopia.txt
 */

public class Ej01Ficheros {

    public static void main(String[] args) {
        // Ejercicio1
        //escribirFichero();
        // Ejercicio2
        //escribirFichero1(5);
        // Ejercicio3
        //escribirFichero1(100);
        // Ejercicio4
        //escribirFichero2();
        // Ejercicio5
        //escribirFichero3();
        // Ejercicio 6
        //escribirFichero4();
        // Ejercicio 7
        copyTxtFile();
        // Ejercicio 8
        //copiarFichero("archivo.txt", "micopia.txt");
        //leerFichero();
        //leerFichero2();
        //copiarFichero("archivo.txt", "copia.txt");
        //crearDirectorio();

    }

    public static void escribirFichero() {
        FileWriter fichero = null;
        PrintWriter escritor = null;

        try {
            fichero = new FileWriter("archivo01.txt", false);
            escritor = new PrintWriter(fichero);
            escritor.println("Estamos haciendo una presentación");
            escritor.println("ahora viene la segunda línea");
            escritor.println("esta es la tercera línea y última");
            escritor.println(JOptionPane.showInputDialog("Escribe tu nombre:"));
            escritor.close();
            fichero.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void escribirFichero1(int lineas) {
        FileWriter fichero = null;
        PrintWriter escritor = null;

        try {
            fichero = new FileWriter("archivo01.txt", false);
            escritor = new PrintWriter(fichero);
            int linea = 0;
            while (linea < lineas) {
                escritor.println("Estamos haciendo una presentación");
                linea++;
                escritor.println("ahora viene la segunda línea");
                linea++;
                escritor.println("esta es la tercera línea y última");
                linea++;
            }
            escritor.close();
            fichero.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void escribirFichero2() {
        FileWriter fichero = null;
        PrintWriter escritor = null;

        try {
            fichero = new FileWriter("archivo01.txt", true);
            escritor = new PrintWriter(fichero);
            escritor.println("Estamos haciendo una presentación");
            escritor.println("ahora viene la segunda línea");
            escritor.println("esta es la tercera línea y última");
            escritor.println(JOptionPane.showInputDialog("Escribe tu nombre:"));
            escritor.close();
            fichero.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void escribirFichero3() {
        FileWriter fichero = null;
        PrintWriter escritor = null;
        String filename = JOptionPane.showInputDialog("Escribe el nombre del archivo");
        try {
            fichero = new FileWriter(filename, false);
            escritor = new PrintWriter(fichero);
            escritor.println("Estamos haciendo una presentación");
            escritor.println("ahora viene la segunda línea");
            escritor.println("esta es la tercera línea y última");
            escritor.println(JOptionPane.showInputDialog("Escribe tu nombre:"));
            escritor.close();
            fichero.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void escribirFichero4() {
        FileWriter fichero = null;
        PrintWriter escritor = null;

        try {
            fichero = new FileWriter("archivo01.txt", true);
            escritor = new PrintWriter(fichero);
            escritor.println(JOptionPane.showInputDialog("Escribe algo:"));
            escritor.close();
            fichero.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void copyTxtFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("archivo.txt"));
            PrintWriter pw = new PrintWriter("archivo_copy.txt");
            String linea = br.readLine();
            while (linea != null) {
                pw.println(linea);
                linea = br.readLine();
            }
            pw.println("Este archivo es una copia");
            pw.close();
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        public static void leerFichero() {
        FileReader lector = null;
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(new File("archivo.txt")));
            String linea = null;
            while ((linea = buffer.readLine()) != null)
                System.out.println(linea);
        } catch (Exception fnfe) {
            fnfe.printStackTrace();
        }
    }

    public static void leerFichero2() {
        try {
            //Nombre del fichero que vamos a leer en todos los ejemplos
            String fileName = "archivo.txt";

            System.out.println("The old-fashioned way");
            BufferedReader is = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = is.readLine()) != null) {
                System.out.println(line);
            }

            //Otras formas de leer ficheros con Java "moderno" usando lambdas y métodos con ::
            System.out.println("Using Path.readAllLines()");
            List<String> lines = Files.readAllLines(Path.of(fileName));

            lines.forEach(System.out::println);

            System.out.println("Using BufferedReader.lines().forEach()");
            new BufferedReader(new FileReader(fileName)).lines().forEach(s -> {
                System.out.println(s);
            });

            System.out.println("Using Path.lines()");
            Files.lines(Path.of(fileName)).forEach(System.out::println);

            System.out.println("Otra forma más de leer ficheros com lambdas");
            final String[] cadena = {""};
            new BufferedReader(new FileReader(fileName)).lines().forEach(s -> {
                cadena[0] += s + "\n";
            });
            System.out.println(cadena[0]);

            //2 soluciones para poner un contador en una función lambda
            System.out.println("Using BufferedReader.lines().forEach()");
            AtomicInteger count = new AtomicInteger(0);
            final int[] iarr = {0};
            new BufferedReader(new FileReader(fileName)).lines().forEach(s -> {
                iarr[0]++;
                System.out.print("Línea " + count.incrementAndGet() + ": ");
                System.out.println(s);
            });

        } catch (Exception io) {
            io.printStackTrace();
        }
    }

    public static void copiarFichero(String original, String copia) {
        try {
            Path originalPath = Paths.get(original);
            Path copied = Paths.get(copia);
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void crearDirectorio() {
        File d = new File("NUEVODIR"); //directorio que creo a partir del actual
        File f1 = new File(d, "FICHERO1.TXT");
        File f2 = new File(d, "FICHERO2.TXT");
        d.mkdir();//CREAR DIRECTORIO
        try {
            f1.createNewFile();
            f2.createNewFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        f1.renameTo(new File(d, "FICHERO1NUEVO"));//renombro FICHERO1

        try {
            File f3 = new File("NUEVODIR/FICHERO3.TXT");
            f3.createNewFile();//crea FICHERO3 en NUEVODIR
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void ficheroBinario() {
        File fichero = new File("fichero.dat");
        FileInputStream fic = null;
        try {
            fic = new FileInputStream(fichero);
            int i;

            while ((i = fic.read()) != -1)
                System.out.println(i);

            fic.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
