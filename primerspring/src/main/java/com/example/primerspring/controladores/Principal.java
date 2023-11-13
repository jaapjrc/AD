package com.example.primerspring.controladores;

import com.example.primerspring.modelo.Producto;
import com.example.primerspring.repositorios.RepositorioProductos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class Principal {
    @Autowired
    RepositorioProductos repositorioProductos;

    @GetMapping("/")
    public String primerapagina(Model model){
//        model.addAttribute("usuario", "Antonio");
//        model.addAttribute("mes", "octubre");

        ArrayList<Producto> lista = repositorioProductos.findAll();
        model.addAttribute("lista", lista);

        return "index";
    }

    @GetMapping("/ubicacion")
    public String ubicacion(Model model){
        return "ubicacion";
    }

    @GetMapping("/privacidad")
    public String privacidad(Model model){
        return "privacidad";
    }

    @GetMapping("/tienda")
    public String tienda(Model model){
        ArrayList<Producto> lista=repositorioProductos.findAll();

        Producto p = new Producto();
        p.setId(1);
        p.setNombre("Bota");
        p.setDescripcion("Doctor Herman");
        p.setPrecio(110);
        p.setImagen("bota.jpg");

        lista.add(p);

        Producto p2 = new Producto();
        p2.setId(2);
        p2.setNombre("Bolsa");
        p2.setDescripcion("Baipas");
        p2.setPrecio(59.99);
        p2.setImagen("bolsa.jpg");

        lista.add(p2);

        Producto p3 = new Producto();
        p3.setId(3);
        p3.setNombre("Sandalias");
        p3.setDescripcion("Cangrejeras");
        p3.setPrecio(59.99);
        p3.setImagen("sandalias.png");

        lista.add(p3);

        Producto p4 = new Producto();
        p4.setId(4);
        p4.setNombre("Chanclas");
        p4.setDescripcion("Lidl");
        p4.setPrecio(59.99);
        p4.setImagen("chanclas.jpg");

        lista.add(p4);


        model.addAttribute("lista", lista);

        return "tienda";
    }

}