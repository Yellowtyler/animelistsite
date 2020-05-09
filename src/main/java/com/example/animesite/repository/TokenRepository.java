package com.example.animesite.repository;

import com.example.animesite.entity.Users;
import com.example.animesite.entity.VerificationToken;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TokenRepository extends CrudRepository<VerificationToken,Long> {


    public VerificationToken findByToken(String token);

    public VerificationToken findByUser(Users user);


    @Modifying
    @Transactional
    public VerificationToken deleteByToken(String token);
}
