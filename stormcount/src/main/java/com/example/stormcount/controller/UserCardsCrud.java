package com.example.stormcount.controller;

import com.example.stormcount.entity.Article;
import com.example.stormcount.entity.Card;
import com.example.stormcount.entity.User;
import com.example.stormcount.entity.UserCards;
import com.example.stormcount.service.CardService;
import com.example.stormcount.service.UserCardsService;
import com.example.stormcount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

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
        User user = userService.findByEmail(authentication.getName());
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

    @PostMapping("/collection/save")
    public String saveUserCard(@ModelAttribute("formUserCards") UserCards newUserCards, Authentication authentication){
        User user = userService.findByEmail(authentication.getName());
        newUserCards.setUser(user);
        userCardsService.save(newUserCards);
        return "redirect:/collection/add";
    }

    @GetMapping("collection/update/{id}")
    public String showUserCard(@PathVariable long id, Model model) {
        LinkedList<Card> cards = cardService.findAll();
        model.addAttribute("cards", cards);
        UserCards uc = userCardsService.findById(id);
        model.addAttribute("formUserCards", uc);
        return "form_add_userCard";
    }

    @PostMapping("/collection/modify")
    public String modifyUserCard(@ModelAttribute("formUserCards") UserCards uc, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        uc.setUser(user);
        userCardsService.save(uc);
        return "redirect:/collection";
    }

    @GetMapping("/collection/delete/{id}")
    public String deleteUserCard(@PathVariable long id, Model model) {
        userCardsService.deleteById(id);
        return "redirect:/collection";
    }

}
