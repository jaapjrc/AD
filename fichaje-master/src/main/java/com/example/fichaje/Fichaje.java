package com.example.fichaje;

import lombok.Data;

import java.sql.Time;
import java.sql.Date;

@Data
public class Fichaje {
    private int id;
    private int idTrabajador;
    private Date fechaEntrada;
    private Time horaEntrada;
    private Date fechaSalida;
    private Time horaSalida;
    private boolean salidaFijada; //Si ya se ha registrado una salida

    public Fichaje() {
    }

    public Fichaje(int id, int idTrabajador, Date fechaEntrada, Time horaEntrada, Date fechaSalida, Time horaSalida, boolean salidaFijada) {
        this.id = id;
        this.idTrabajador = idTrabajador;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.salidaFijada = salidaFijada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public boolean isSalidaFijada() {
        return salidaFijada;
    }

    public void setSalidaFijada(boolean salidaFijada) {
        this.salidaFijada = salidaFijada;
    }

    @Override
    public String toString() {
        return "Fichaje{" +
                "id=" + id +
                ", idTrabajador=" + idTrabajador +
                ", fechaEntrada=" + fechaEntrada +
                ", horaEntrada=" + horaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", horaSalida=" + horaSalida +
                ", salidaFijada=" + salidaFijada +
                '}';
    }
}
