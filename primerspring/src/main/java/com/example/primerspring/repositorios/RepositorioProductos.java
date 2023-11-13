package com.example.primerspring.repositorios;

import com.example.primerspring.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepositorioProductos extends JpaRepository<Producto, Long> {
    public ArrayList<Producto> findAll();
    public Producto findById(long id);
    public Producto findByNombre(String nombre);

}
