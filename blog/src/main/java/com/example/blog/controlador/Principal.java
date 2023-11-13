package com.example.blog.controlador;

import com.example.blog.modelo.Entrada;
import com.example.blog.repositorio.RepositorioEntradas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class Principal {
    @Autowired
    RepositorioEntradas repositorioEntradas;

    @GetMapping({"/", "/blog"})
    public String primerapagina(Model model) {
        ArrayList<Entrada> lista = repositorioEntradas.findAll();
        model.addAttribute("lista", lista);

        return "index";
    }

    @GetMapping("/entrada/{id}")
    public String entrada(@PathVariable long id, Model model) {
        Entrada entrada = repositorioEntradas.findById(id);
        model.addAttribute("entrada", entrada);
        return "detalle";
    }
}