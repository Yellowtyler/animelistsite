package com.example.animesite.service;

import com.example.animesite.entity.Users;
import com.example.animesite.entity.VerificationToken;
import com.example.animesite.repository.TokenRepository;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImp implements TokenService {



    @Autowired
    TokenRepository tokenRepository;


    @Override
    public VerificationToken findByToken(String token) {

        return tokenRepository.findByToken(token);
    }

    @Override
    public VerificationToken findByUser(Users user) {

        return tokenRepository.findByUser(user);
    }

    @Override
    public void save(VerificationToken token) {
        tokenRepository.save(token);

    }

    @Override
    public VerificationToken deleteByToken(String token) {
        return tokenRepository.deleteByToken(token);
    }


}
