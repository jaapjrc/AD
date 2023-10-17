package com.example.todo;

public class Tarea {
    private int id;
    private String tarea;
    private boolean terminada;

    public Tarea() {
    }

    public Tarea(int id, String tarea, boolean terminada) {
        this.id = id;
        this.tarea = tarea;
        this.terminada = terminada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public boolean isTerminada() {
        return terminada;
    }

    public void setTerminada(boolean terminada) {
        this.terminada = terminada;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", tarea='" + tarea + '\'' +
                ", terminada=" + terminada +
                '}';
    }
}
