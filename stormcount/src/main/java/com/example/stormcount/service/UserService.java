package com.example.stormcount.service;

import com.example.stormcount.dto.UserDto;
import com.example.stormcount.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

}
