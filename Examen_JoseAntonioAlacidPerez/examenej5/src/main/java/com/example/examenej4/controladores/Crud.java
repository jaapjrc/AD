package com.example.examenej4.controladores;

import com.example.examenej4.modelo.Producto;
import com.example.examenej4.servicios.ServicioProductos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
public class Crud {
    @Autowired
    ServicioProductos servicioProductos;

    @GetMapping("/")
    public String listadoProductos(Model model){
        model.addAttribute("productos", servicioProductos.findAll());
        return "crud";
    }

    @GetMapping("/add")
    public String addProducto(Model model){
        //formPelicula es el objeto que voy a usar en la plantilla
        model.addAttribute("formProducto", new Producto());
        return "form_add";
    }

    @PostMapping("/save")
    public String guardarProducto(@ModelAttribute("formPelicula") Producto nuevoProducto){


        servicioProductos.save(nuevoProducto);
        return "redirect:/add";
    }

    @GetMapping("/update/{id}")
    public String muestraProducto(@PathVariable long id, Model model){
        Producto p= servicioProductos.findById(id);
        //El nombre del objeto debe ser el mismo que en el GetMapping de añadir
        //Y el mismo que en el th:object del formulario
        model.addAttribute("formProducto", p);
        return "form_add";
    }

    @PostMapping("/modificar")
    public String modificarProducto(@ModelAttribute("formProducto") Producto p){
        servicioProductos.save(p);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String borrarProducto(@PathVariable long id, Model model){

        servicioProductos.deleteById(id);
        return "redirect:/";
    }

}
