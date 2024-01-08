package com.example.examenej4.servicios;

import com.example.examenej4.modelo.Producto;
import com.example.examenej4.repositorios.RepositorioProductos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioProductos {
    @Autowired
    RepositorioProductos repo;

    public ArrayList<Producto> findAll(){
        return repo.findAll();
    }

    public Producto findById(long id){
        return repo.findById(id);
    }

    public Producto save(Producto p){
        return repo.save(p);
    }

    public void deleteById(long id){
        repo.deleteById(id);
    }
}
