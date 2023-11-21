package com.example.peliculon.controladores;

import com.example.peliculon.modelo.Pelicula;
import com.example.peliculon.repositorios.RepositorioComentarios;
import com.example.peliculon.repositorios.RepositorioPeliculas;
import com.example.peliculon.servicios.ServicioComentarios;
import com.example.peliculon.servicios.ServicioPeliculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class Principal {

    @Autowired
    ServicioPeliculas servicioPeliculas;
    @Autowired
    ServicioComentarios servicioComentarios;

    @GetMapping("/")
    public String inicio(Model model){
        ArrayList<Pelicula> cartelera=servicioPeliculas.findAll();
        model.addAttribute("cartelera", cartelera);
        return "index";
    }

    //Esta es la dirección que tengo que escribir en el navegador
    //Esta es la dirección que tengo que escribir en el enlace de index.html
    //Lo que sale en el <a th:href="/pelicula/{id}>
    @GetMapping("/pelicula/{id}")
    public String pelicula(@PathVariable long id, Model model){

        Pelicula p=servicioPeliculas.findById(id);
        //El nombre de "pelicula" es el que voy a usar en la vista detalle.html
        model.addAttribute("pelicula", p);

        model.addAttribute("comentarios", servicioComentarios.findByPelicula(p));

        //El nombre que pongo en el return es el que tendrá el archivo .html
        return "detalle";
    }
}










