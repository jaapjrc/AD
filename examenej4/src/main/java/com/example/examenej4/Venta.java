package com.example.examenej4;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String articulo;
    @Column(name = "cantidad_vendida")
    private int cantidadVendida;
    private double precio;
    private double importe;

}
