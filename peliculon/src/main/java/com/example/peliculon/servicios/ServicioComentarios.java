package com.example.peliculon.servicios;

import com.example.peliculon.modelo.Comentario;
import com.example.peliculon.modelo.Pelicula;
import com.example.peliculon.repositorios.RepositorioComentarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioComentarios {
    @Autowired
    RepositorioComentarios repo;

    public ArrayList<Comentario> findAll(){
        return repo.findAll();
    }

    public Comentario findById(long id){
        return repo.findById(id);
    }

    public ArrayList<Comentario> findByPelicula(Pelicula pelicula){
        return repo.findByPelicula(pelicula);
    }

    public Comentario save(Comentario comentario){
        return repo.save(comentario);
    }

    public void delete(Comentario comentario){
        repo.delete(comentario);
    }

}
