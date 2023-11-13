package com.example.blog.repositorio;

import com.example.blog.modelo.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepositorioEntradas extends JpaRepository<Entrada, Long> {
    public ArrayList<Entrada> findAll();
    public Entrada findById(long id);
    public Entrada findByTitulo(String titulo);

}