package com.example.todo;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValues(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="tarea")
    private String tarea;
    @Column(name="terminada")
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
