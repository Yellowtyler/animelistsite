package com.example.animesite.event;

import com.example.animesite.entity.MyUserDetails;
import com.example.animesite.entity.Users;
import com.example.animesite.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.mail.MailSender;

import java.util.UUID;

@Component
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {


    @Autowired
    private MailSender mailSender;

    @Autowired
    private MessageSource messages;

    @Autowired
   private MyUserDetailsService myUserDetailsService;

    @Override
    public void onApplicationEvent(OnRegistrationSuccessEvent onRegistrationSuccessEvent) {

        this.confirmRegistration(onRegistrationSuccessEvent);
    }

    private void confirmRegistration(OnRegistrationSuccessEvent event) {

        Users user = event.getUser();
        String token = UUID.randomUUID().toString();
        myUserDetailsService.createVerificationToken(user,token);

        String recipient = user.getEmail();
        String subject = "Registration Confirmation";
        String url
                = event.getAppUrl() + "/confirmreg?token=" + token;
        String message = messages.getMessage("To confirm registration, go to the following link: ", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipient);
        email.setSubject(subject);
        email.setText(message + "http://localhost:8080" + url);
        System.out.println(url);
        mailSender.send(email);


    }
}
