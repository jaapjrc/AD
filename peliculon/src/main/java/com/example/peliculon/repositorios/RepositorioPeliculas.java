package com.example.peliculon.repositorios;

import com.example.peliculon.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface RepositorioPeliculas extends JpaRepository<Pelicula, Long> {
    public ArrayList<Pelicula> findAll();
    public Pelicula findById(long id);
    public Pelicula save(Pelicula pelicula);
}
