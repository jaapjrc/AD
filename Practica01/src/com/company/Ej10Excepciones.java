package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/* Ejercicios
        1-Cambia el nombre del archivo por el de uno que sí exista en la carpeta del proyecto y comprueba el resultado
        2-Añade un bloque finally en el que se cierre el objeto BufferedReader
        3-
 */

public class Ej10Excepciones {

    public static BufferedReader br;
    public static void main(String[] args) throws IOException {
        try{
            // Ejercicio 1
            br=new BufferedReader(new FileReader("archivo.txt"));
        }catch(FileNotFoundException fe){
            System.out.println("No se ha encontrado el archivo");
            // Ejercicio 2
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//        Scanner sc=new Scanner(System.in);
//        try {
//            int n = sc.nextInt();
//        }catch (Exception e){

        }
        //System.out.println("Tu número es: " + n);
    }

