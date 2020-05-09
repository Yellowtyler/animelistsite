package com.example.animesite.controller;

import com.example.animesite.entity.Users;
import com.example.animesite.repository.UserRepository;
import com.example.animesite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {


    @Autowired
    UserDetailsService userService;

    @GetMapping("/login")
    public String login(@RequestParam(required = false, name="error") String error,
                        Model model)
    {

        if(error!=null)
        {
            model.addAttribute("error", "Username or password isn't valid\nor account isn't activated.");
        }
        return "login";
    }





//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String postLogin( @RequestParam(required = false, name="username") String username,
//                             @RequestParam(required = false, name="password") String password)
//    {
//            UserDetails user = userService.loadUserByUsername(username);
//
//            if(!user.get().getActive())
//            {
//                return "redirect:/loginfailure";
//            }
//
//
//
//        return "login";
//    }


}
