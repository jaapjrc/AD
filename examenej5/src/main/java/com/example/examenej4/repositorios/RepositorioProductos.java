package com.example.examenej4.repositorios;

import com.example.examenej4.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepositorioProductos extends JpaRepository<Producto, Long> {
    public ArrayList<Producto> findAll();

    public Producto findById(long id);

    public Producto save(Producto p);
}
