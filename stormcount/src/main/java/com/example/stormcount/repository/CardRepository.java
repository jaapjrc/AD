package com.example.stormcount.repository;

import com.example.stormcount.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    LinkedList<Card> findAll();
    Card findById(long id);
    Card findByCardname(String cardname);
}
