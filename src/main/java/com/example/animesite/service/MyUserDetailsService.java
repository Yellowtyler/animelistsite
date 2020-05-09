package com.example.animesite.service;

import com.example.animesite.entity.MyUserDetails;
import com.example.animesite.entity.Users;
import com.example.animesite.entity.VerificationToken;
import com.example.animesite.event.CustomExceptionHandler;
import com.example.animesite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private  UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    CustomExceptionHandler customExceptionHandler;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> user= userService.findByLogin(username);

        if(user==null)
        {
             throw new UsernameNotFoundException("Invalid username or password.");
            // customExceptionHandler.handle(new UsernameNotFoundException("Invalid username or password."));
        }

        try {

            if (!user.get().getActive()) {
                customExceptionHandler.handle(new UsernameNotFoundException("Please enable your account."));
                throw new UsernameNotFoundException("Please enable your account.");

            }
            else {
                return  user.map(MyUserDetails::new).get();
            }
        }
        catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }

        throw new UsernameNotFoundException("Please enable your account.");

    }


    @Transactional
    public void createVerificationToken(Users user, String token) {
        VerificationToken newUserToken = new VerificationToken(token, user);
        tokenService.save(newUserToken);
    }


    @Transactional
    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenService.findByToken(verificationToken);
    }


}
