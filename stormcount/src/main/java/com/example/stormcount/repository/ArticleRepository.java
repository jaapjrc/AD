package com.example.stormcount.repository;

import com.example.stormcount.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    LinkedList<Article> findAll();

    Article findById(long id);

    Article save(Article article);

    LinkedList<Article> findFirst3ByOrderByIdDesc();


}
