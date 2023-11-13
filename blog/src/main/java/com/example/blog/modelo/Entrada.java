package com.example.blog.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Entradas")
@Data
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String texto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
