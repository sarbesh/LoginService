package com.sarbesh.loginservice.security;

import com.sarbesh.core.security.CustomWebSecurityConfigurer;
import com.sarbesh.loginservice.service.impl.LoginUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomSecurityConfig implements CustomWebSecurityConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSecurityConfig.class);

    PasswordEncoder passwordEncoder;

    LoginUserDetailsService loginUserDetailsService;

    @Autowired
    public CustomSecurityConfig(PasswordEncoder passwordEncoder, LoginUserDetailsService loginUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.loginUserDetailsService = loginUserDetailsService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.info("#CustomSecurityConfig setting custom authentication");
        auth.userDetailsService(loginUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

    }
}
