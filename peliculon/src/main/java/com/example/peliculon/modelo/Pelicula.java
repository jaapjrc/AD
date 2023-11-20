package com.example.peliculon.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@Entity
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String sinopsis;
    private LocalDate fecha;
    private String nacionalidad;
    private String imagen;
    private String trailer;

    /*@OneToMany
    private ArrayList<Comentario> comentarios;*/
}
