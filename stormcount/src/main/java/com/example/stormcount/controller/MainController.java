package com.example.stormcount.controller;

import com.example.stormcount.entity.Article;
import com.example.stormcount.service.ArticleService;
import com.example.stormcount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public String main(Model model) {
        LinkedList<Article> articles = articleService.findFirst3ByOrderByIdDesc();
        model.addAttribute("articles", articles);
        return "home";
    }

    @GetMapping("/article/{id}")
    public String article(@PathVariable long id, Model model) {
        Article a = articleService.findById(id);
        model.addAttribute("a", a);
        return "article";
    }

    @GetMapping("/articles")
    public String articles(Model model) {
        LinkedList<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "articles";
    }

}
