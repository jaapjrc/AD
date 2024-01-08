package com.example.examenej4.modelo;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String pelicula;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate fechaPrestamo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate fechaDevolucion;

    @OneToOne
    private Producto producto;
}
