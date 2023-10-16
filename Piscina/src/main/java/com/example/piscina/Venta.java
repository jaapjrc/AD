package com.example.piscina;

import java.time.LocalDate;
import java.time.LocalTime;

public class Venta {
    private int id;
    private int adultos;
    private int menores;
    private int precioAdultos;
    private int precioMenores;
    private LocalDate fecha;
    private LocalTime hora;

    public Venta() {
    }

    public Venta(int id, int adultos, int menores, LocalDate fecha, LocalTime hora, int precioMenores, int precioAdultos) {
        this.id = id;
        this.adultos = adultos;
        this.menores = menores;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdultos() {
        return adultos;
    }

    public void setAdultos(int adultos) {
        this.adultos = adultos;
    }

    public int getMenores() {
        return menores;
    }

    public void setMenores(int menores) {
        this.menores = menores;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getPrecioAdultos() {
        return precioAdultos;
    }

    public void setPrecioAdultos(int precioAdultos) {
        this.precioAdultos = precioAdultos;
    }

    public int getPrecioMenores() {
        return precioMenores;
    }

    public void setPrecioMenores(int precioMenores) {
        this.precioMenores = precioMenores;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", adultos=" + adultos +
                ", menores=" + menores +
                ", fecha=" + fecha +
                ", hora=" + hora +
                '}';
    }
}
