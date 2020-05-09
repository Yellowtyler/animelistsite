package com.example.animesite.controller;


import com.example.animesite.configuration.PasswordConfig;
import com.example.animesite.entity.Users;
import com.example.animesite.entity.VerificationToken;
import com.example.animesite.event.OnRegistrationSuccessEvent;
import com.example.animesite.repository.UserRepository;
import com.example.animesite.service.MyUserDetailsService;
import com.example.animesite.service.TokenService;
import com.example.animesite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;

@Controller
public class RegController {

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    UserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private TokenService tokenService;

    @GetMapping(value = "/reg")
    public String getReg()
    {

        return "reg";

    }


    @PostMapping(value = "/reg")
    public String postReg(@RequestParam(required=false, name = "username") String username,
                          @RequestParam(required=false, name = "password") String password,
                          @RequestParam(required=false, name = "email") String email,
                          @RequestParam(required=false, name = "confirm_password") String confirm_password,
                          @RequestParam(required = false, name="UserError") String UserError,
                          @RequestParam(required = false, name="EmailError") String EmailError,
                          WebRequest request,
                          Model model)
    {


                Optional<Users> checkUser = userService.findByLogin(username);
                Optional<Users> checkEmail = userService.findByEmail(email);


                if(!checkUser.isEmpty())
                {

                    model.addAttribute("UserError","Error: User already exists!");
                    return "reg";

                }

                else if(!checkEmail.isEmpty())
                {

                    model.addAttribute("EmailError","Error: A user with this email address already exists!");
                    return "reg";

                }

                Users user = new Users();
                user.setLogin(username);
                user.setPassword(passwordConfig.passwordEncoder().encode(password));
                user.setEmail(email);
                user.setRoles("USER");
                user.setActive(false);


                try {
                    String appUrl = request.getContextPath();
                    eventPublisher.publishEvent(new OnRegistrationSuccessEvent(user, request.getLocale(),appUrl));
                }catch(Exception re) {
                    re.printStackTrace();

                }

                userService.save(user);

                return "redirect:/registrationSuccess";
    }



    @GetMapping("/confirmreg")
    public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
        Locale locale=request.getLocale();
        VerificationToken verificationToken = myUserDetailsService.getVerificationToken(token);
        if(verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/access-denied";
        }
        Users user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if((verificationToken.getExpiryDate().getTime()-calendar.getTime().getTime())<=0) {
            String message = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", message);
            tokenService.deleteByToken(verificationToken.getToken());
            return "redirect:/access-denied";
        }


        user.setActive(true);
        userService.updateUser(user.getActive(),user.getLogin());
        return null;
    }


    @GetMapping(value="/registrationSuccess")
    public String registrationSuccess()
    {
        return "registrationSuccess";
    }

}
