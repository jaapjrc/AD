package com.example.stormcount.service;

import com.example.stormcount.entity.Card;
import com.example.stormcount.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class CardService {
    @Autowired
    CardRepository repo;

    public LinkedList<Card> findAll() { return repo.findAll(); }

    public Card findCardByCardname(String string) { return repo.findByCardname(string); }

    public void save(Card c) { repo.save(c);}


}
