package com.example.peliculon.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    @ManyToOne
    private Pelicula pelicula;

}
