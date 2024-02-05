package com.example.stormcount.controller;

import com.example.stormcount.entity.Article;
import com.example.stormcount.service.ArticleService;
import com.example.stormcount.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
public class ArticleCrud {
    @Autowired
    ArticleService articleService;

    @Autowired
    public StorageService storageService;

    @GetMapping("/crud")
    public String articleListing(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "crud";
    }

    @GetMapping("/crud/add")
    public String addArticle(Model model) {
        model.addAttribute("formArticle", new Article());
        return "form_add";
    }

    @PostMapping("crud/save")
    public String saveArticle(@ModelAttribute("formArticle") Article newArticle,
                              @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            String img = storageService.store(file, newArticle.getTitle());
            System.out.println("The image to save is : " + img);
            newArticle.setImg(MvcUriComponentsBuilder
                    .fromMethodName(com.example.stormcount.controladores.FileUploadController.class, "serveFile", img).build().toUriString());
        }

        articleService.save(newArticle);
        return "redirect:/crud/add";
    }

    @GetMapping("crud/update/{id}")
    public String showArticle(@PathVariable long id, Model model) {
        Article a = articleService.findById(id);
        model.addAttribute("formArticle", a);
        return "form_add";
    }

    @PostMapping("/crud/modify")
    public String modifyArticle(@ModelAttribute("formArticle") Article a,
                                @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            String img = storageService.store(file, a.getTitle());
            System.out.println("The image to save is : " + img);
            a.setImg(MvcUriComponentsBuilder
                    .fromMethodName(com.example.stormcount.controladores.FileUploadController.class, "serveFile", img).build().toUriString());
        }

        articleService.save(a);
        return "redirect:/crud";
    }

    @GetMapping("/crud/delete/{id}")
    public String deleteArticle(@PathVariable long id, Model model) {
        articleService.deleteById(id);
        return "redirect:/crud";
    }
}
