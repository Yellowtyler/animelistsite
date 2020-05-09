package com.example.animesite.event;

import com.example.animesite.entity.Users;
import org.springframework.context.ApplicationEvent;
import java.util.Locale;
public class OnRegistrationSuccessEvent extends ApplicationEvent {

    private static final long serialVersionUID=1L;
    private String appUrl;
    private Users user;
    private Locale locale;

    public OnRegistrationSuccessEvent(Users user, Locale locale, String appUrl) {
        super(user);
        this.user=user;
        this.appUrl = appUrl;
        this.locale = locale;
    }


    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
