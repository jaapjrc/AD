package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Ejercicios
    1-Pregunta con Scanner el nombre y apellidos de una persona y guárdalo usando mapper.readValue como en el ejemplo

 */

public class Ej09JSONJackson {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonInput ="{\"id\":0,\"nombre\":\"Robin\",\"apellidos\":\"Wilson\"}";
        Persona q = mapper.readValue(jsonInput, Persona.class);
        System.out.println("Read and parsed Persona from JSON: " + q.toString());

        Persona p = new Persona(1, "Roger", "Rabbit");
        //System.out.print("Persona objeto " + p +" as JSON = ");
        //mapper.writeValue(System.out, p);
        mapper.writeValue(new File("persona.json"), p);

        //Ejercicio 1
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu nombre:");
        String nombre = sc.nextLine();
        System.out.println("Introduce tus apellidos:");
        String apellidos = sc.nextLine();
        Persona p1 = new Persona(2, nombre, apellidos);
        mapper.writeValue(new File("persona.json"), p1);

        //Con Jackson puedo guardar una lista completa
        ArrayList<Persona> lista=new ArrayList<>();
        lista.add(p);
        lista.add(q);
        mapper.writeValue(new File("personas.json"), lista);

        //Leo una lista completa de personas de un archivo JSON
        List<Persona> otraLista = mapper.readValue(new File("personas.json"), new com.fasterxml.jackson.core.type.TypeReference<List<Persona>>(){});
        System.out.println(otraLista.toString());
        for (Persona persona : otraLista) {
            System.out.println(persona.toString());
        }
    }
}

class Persona implements Serializable {
    private int id;
    private String nombre;
    private String apellidos;
    public Persona(){

    }
    public Persona(int id, String nombre, String apellidos){
        this.nombre=nombre;
        this.apellidos=apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}