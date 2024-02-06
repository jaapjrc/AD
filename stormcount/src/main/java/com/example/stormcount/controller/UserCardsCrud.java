package com.example.stormcount.controller;

import com.example.stormcount.entity.Article;
import com.example.stormcount.entity.Card;
import com.example.stormcount.entity.User;
import com.example.stormcount.entity.UserCards;
import com.example.stormcount.service.CardService;
import com.example.stormcount.service.UserCardsService;
import com.example.stormcount.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;

@Controller
public class UserCardsCrud {
    @Autowired
    CardService cardService;

    @Autowired
    UserService userService;

    @Autowired
    UserCardsService userCardsService;

    @GetMapping("/collection")
    public String collection(Model model, Authentication authentication) {
        User user = userService.findByEmail(authentication.name());
        LinkedList<UserCards> cards = userCardsService.findAllByUser(user);
        model.addAttribute("cards", cards);
        return "collection";
    }

    @GetMapping("/collection/add")
    public String addUserCard(Model model) {
        LinkedList<Card> cards = cardService.findAll();
        model.addAttribute("cards", cards);
        model.addAttribute("formUserCards", new UserCards());
        return "form_add_userCard";
    }

}
