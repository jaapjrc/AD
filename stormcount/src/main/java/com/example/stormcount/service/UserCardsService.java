package com.example.stormcount.service;

import com.example.stormcount.entity.User;
import com.example.stormcount.entity.UserCards;
import com.example.stormcount.repository.UserCardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class UserCardsService {
    @Autowired
    UserCardsRepository userCardsRepository;

    public LinkedList<UserCards> findAllByUser(User user) {
        return userCardsRepository.findAllByUser(user);
    }

    public void save(UserCards userCards){ userCardsRepository.save(userCards);}

    public UserCards findById(long id){ return userCardsRepository.findById(id);}

    public void deleteById(long id) { userCardsRepository.deleteById(id); }
}
