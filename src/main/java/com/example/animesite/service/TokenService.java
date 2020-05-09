package com.example.animesite.service;

import com.example.animesite.entity.Users;
import com.example.animesite.entity.VerificationToken;
import org.springframework.stereotype.Service;


public interface TokenService {

    public VerificationToken findByToken(String token);

    public VerificationToken findByUser(Users user);

    public void save(VerificationToken token);

    public VerificationToken deleteByToken(String token);

}
