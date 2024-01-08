package com.example.stormcount.service.impl;

import com.example.stormcount.dto.UserDto;
import com.example.stormcount.entity.Role;
import com.example.stormcount.entity.User;
import com.example.stormcount.repository.RoleRepository;
import com.example.stormcount.repository.UserRepository;
import com.example.stormcount.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        /*String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);*/
        userDto.setId(user.getId());
        userDto.setFirstName(user.getName());
        userDto.setLastName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(long id){
        return userRepository.findById(id);
    }

    public User findByUsername(String username){return userRepository.findByName(username);}

    public User save(User usuario){
        userRepository.save(usuario);
        return usuario;
    }

    public void delete(User usuario){
        userRepository.delete(usuario);
    }
    public void deleteById(long id){
        userRepository.deleteById(id);
    }
}