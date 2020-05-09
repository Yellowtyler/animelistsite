package com.example.animesite.service;

import com.example.animesite.entity.Users;

import java.util.Optional;

public interface UserService {



    Optional<Users> findByLogin(String login);

    Optional<Users> findByEmail(String email);

    void updateUser(boolean active,String username);

    void save(Users user);

    void deleteByLogin(String login);
}
