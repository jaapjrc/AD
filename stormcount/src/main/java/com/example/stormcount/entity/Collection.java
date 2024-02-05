package com.example.stormcount.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;

@Data
@Entity
@Table(name = "collections")
public class  Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private LinkedList<Card> cards;

    @OneToOne
    private User user;

}
