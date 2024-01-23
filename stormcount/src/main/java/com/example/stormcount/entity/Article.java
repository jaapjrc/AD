package com.example.stormcount.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String text;
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate publishing;
    private String img;
    private String author;
}
