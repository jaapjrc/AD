package com.example.stormcount.repository;

import com.example.stormcount.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(long id);
    User findByName(String name);
}