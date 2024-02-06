package com.example.stormcount.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;

@Data
@Entity
@Table(name = "user_cards")
public class UserCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Card card;

    @ManyToOne
    private User user;

    @Column
    private int quantity;

}
