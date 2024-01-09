package com.example.stormcount.controller;

import com.example.stormcount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String main(Model model){
        return "home";
    }
}
