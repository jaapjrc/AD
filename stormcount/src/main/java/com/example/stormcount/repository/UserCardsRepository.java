package com.example.stormcount.repository;

import com.example.stormcount.entity.Article;
import com.example.stormcount.entity.Card;
import com.example.stormcount.entity.User;
import com.example.stormcount.entity.UserCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface UserCardsRepository extends JpaRepository<UserCards, Long> {
    LinkedList<UserCards> findAllByUser(User user);

    UserCards findById(long id);
}
