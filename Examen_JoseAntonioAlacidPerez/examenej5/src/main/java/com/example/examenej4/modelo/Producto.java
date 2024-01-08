package com.example.examenej4.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String marca;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private TipoProducto tipoProducto;

//    Lo comento para que no me de problemas al ejecutarlo, si tuviera más tiempo añadiria un prestamo
//            a la base de datos y modificaria los htmls para que salga el prestamo tambien
//    @OneToOne
//    private Prestamo prestamo;
}
