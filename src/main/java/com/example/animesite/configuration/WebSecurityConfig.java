package com.example.animesite.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder,
                             UserDetailsService userDetailsService) {

        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }


    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
http.csrf().disable()
        .authorizeRequests()
       // .antMatchers("/list","/search").hasRole("USER")
       // .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/","/main","/css/*","/js/*","/login","/reg","/confirmreg","/registrationSuccess", "/img/*").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
         .loginPage("/login")
         .loginProcessingUrl("/login")
         .defaultSuccessUrl("/",true)
         .failureUrl("/login?error=1")
         .passwordParameter("password")
         .usernameParameter("username")
         .and()
        .rememberMe()
         .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
         .key("secured")
         .rememberMeParameter("remember-me")
        .and()
        .logout()
         .logoutUrl("/logout")
         .clearAuthentication(true)
         .invalidateHttpSession(true)
         .deleteCookies("JSESSIONID","remember-me")
         .logoutSuccessUrl("/");;

    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resources/static/**");
//    }
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//       //return new BCryptPasswordEncoder(10);
//          return NoOpPasswordEncoder.getInstance();
//    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService()
//    {
//        UserDetails danUser= User.builder()
//                .username("dan")
//                .password(passwordEncoder.encode("1234567890"))
//                .roles("USER")
//                .build();
//
//        UserDetails dan1User= User.builder()
//                .username("dan1")
//                .password(passwordEncoder.encode("1234"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(danUser,dan1User);
//    }


}
