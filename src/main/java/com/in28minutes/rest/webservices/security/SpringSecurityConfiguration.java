package com.in28minutes.rest.webservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {


        //all requests must be authenticated
        httpSecurity.authorizeHttpRequests(

                auth->auth.anyRequest().authenticated()

        );


        httpSecurity.httpBasic(Customizer.withDefaults()); //show pop up for entering user name and password

        //to enable post put requests
            httpSecurity.csrf().disable();



        return httpSecurity.build();
    };



}
