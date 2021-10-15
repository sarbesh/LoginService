package com.sarbesh.loginservice.service.impl;

import com.sarbesh.loginservice.dto.UserDetailsImpl;
import com.sarbesh.loginservice.model.UserAuth;
import com.sarbesh.loginservice.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAuth dbUser = userAuthRepository.findByEmail(email).orElseThrow(() ->new RuntimeException("User Not Found"));
        return new UserDetailsImpl(dbUser);
    }
}
