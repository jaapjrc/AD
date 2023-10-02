package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

public class Ej04CSV {

    public static void copiarFichero(String original, String copia){
        try {
            Path originalPath = Paths.get(original);
            Path copied = Paths.get(copia);
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void addCountry() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("poblacion.csv"));
            PrintWriter pw = new PrintWriter("temp.csv");
            String linea=br.readLine();
            while (linea != null){
                pw.println(linea + ";Spain");
                linea= br.readLine();
            }
            pw.close();
            br.close();
            copiarFichero("temp.csv", "poblacion.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void escribeSpain(){
        try {
            BufferedReader br=new BufferedReader(new FileReader("poblacion.csv"));
            ArrayList<Provincia> lista=new ArrayList<>();
            String linea=br.readLine();
            while(linea!=null){
                String[] campos=linea.split(";");
                Provincia p=new Provincia(campos[0], Integer.parseInt(campos[1]), "España");
                lista.add(p);
                linea=br.readLine();
            }
            br.close();
            PrintWriter pw=new PrintWriter(new FileWriter("poblacion.csv"));
            for(Provincia p: lista){
                linea=p.toCSV();
                pw.println(linea);
                //linea=p.getNombre() + ";" + p.getHabitantes() + ";" + p.getPais();
            }
            pw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        //Creamos un arrayList de Provincia que es una clase con dos propiedades: nombre y número habitantes
        addCountry();
        ArrayList<Provincia> pais=new ArrayList<Provincia>();

        try {
            //Abrimos para lectura el archivo CSV.
            // Es un archivo de texto que separa "registros" por línea y campos con ";" Ejemplo: Murcia; 1511251
            BufferedReader br = new BufferedReader(new FileReader("poblacion.csv"));
            String linea=br.readLine();
            while(linea!=null) {
                //Cada línea de texto, la separamos con split en un array. "Cortamos" donde esté el ";"
                String[] datos = linea.split(";");

                //Mostramos en la consola el contenido del Array
                System.out.println(Arrays.toString(datos));

                //Guardamos en la variable nombre, el valor de la primera posición del array
                String nombre = datos[0];

                //Guardamos el número de habitantes en strHabitantes y después lo convertimos en un entero
                String strHabitantes=datos[1];
                int habitantes=Integer.parseInt(strHabitantes);
                //int habitantes = (!IntegerValidator.getInstance().isValid(strHabitantes))? 0 : Integer.parseInt(strHabitantes);
                String country = datos[2];
                //Con los valores de las variables antes guardados, creamos un objeto de la clase provincia
                Provincia aux = new Provincia(nombre, habitantes, country);

                //Añadimos el objeto provincia al ArrayList pais
                pais.add(aux);
                linea = br.readLine();
            }

        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }


}
class Provincia{
    private String nombre, pais;
    private int habitantes;

    public Provincia(String nombre, int habitantes, String pais) {
        this.nombre = nombre;
        this.habitantes = habitantes;
        this.pais = pais;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getHabitantes() {
        return habitantes;
    }
    public void setHabitantes(int habitantes) {
        this.habitantes = habitantes;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String toCSV(){
        return nombre + ";" + habitantes + ";" + pais;
    }
}
