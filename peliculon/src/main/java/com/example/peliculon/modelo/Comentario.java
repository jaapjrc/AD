package com.example.peliculon.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    @ManyToOne
    private Pelicula pelicula;
}
