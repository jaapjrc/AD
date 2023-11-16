package com.example.peliculon.controlador;

import com.example.peliculon.modelo.Pelicula;
import com.example.peliculon.repositorios.RepositorioPeliculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class Principal {
    @Autowired
    RepositorioPeliculas repositorioPeliculas;

    @GetMapping("/")
    public String inicio(Model model){
        ArrayList<Pelicula> cartelera = repositorioPeliculas.findAll();
        model.addAttribute("cartelera", cartelera);

        //model.addAttribute("cartelera", repositorioPeliculas.findAll());
        return "index";
    }

    @GetMapping("/pelicula/{id}")
    public String pelicula(@PathVariable long id, Model model) {
        Pelicula pelicula = repositorioPeliculas.findById(id);
        model.addAttribute("pelicula", pelicula);
        return "detalle";
    }
}
