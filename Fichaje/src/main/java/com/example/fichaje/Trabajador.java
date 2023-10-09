package com.example.fichaje;

import lombok.Data;


public class Trabajador {

    private String nombre;
    private int id;
    private String apellidos;
    private String dni;
    private String departamento;

    public Trabajador() {
    }

    public Trabajador(String nombre, int id, String apellidos, String dni, String departamento) {
        this.nombre = nombre;
        this.id = id;
        this.apellidos = apellidos;
        this.dni = dni;
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
