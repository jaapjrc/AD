package com.example.stormcount.service;

import com.example.stormcount.entity.Article;
import com.example.stormcount.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository repo;

    public LinkedList<Article> findAll() {
        return repo.findAll();
    }

    public Article findById(long id) { return repo.findById(id); }

    public Article save(Article article) { return repo.save(article); }

    public void deleteById(long id) { repo.deleteById(id);}

    public LinkedList<Article> findFirst3ByOrderByIdDesc(){ return repo.findFirst3ByOrderByIdDesc(); }
}
