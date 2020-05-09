package com.example.animesite.service;


import com.example.animesite.entity.Users;
import com.example.animesite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;




    @Override
    public Optional<Users> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateUser(boolean active, String username) {
        userRepository.updateUser(active, username);
    }

    @Override
    public void save(Users user) {
        userRepository.save(user);
    }

    @Override
    public void deleteByLogin(String login) {
        userRepository.deleteByLogin(login);
    }
}
