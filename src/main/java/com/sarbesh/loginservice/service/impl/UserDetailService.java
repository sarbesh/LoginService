package com.sarbesh.loginservice.service.impl;

import com.sarbesh.core.security.JwtHelper;
import com.sarbesh.loginservice.dto.request.PasswordLogin;
import com.sarbesh.loginservice.dto.request.PasswordReset;
import com.sarbesh.loginservice.dto.request.UserUpdateRq;
import com.sarbesh.loginservice.dto.response.UserUpdateRs;
import com.sarbesh.loginservice.model.UserAuth;
import com.sarbesh.loginservice.repository.UserAuthRepository;
import com.sarbesh.loginservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailService implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginUserDetailsService loginUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserUpdateRs registerUser(PasswordLogin passwordLogin){
        LOGGER.info("#UserDetailService Registering new user {}",passwordLogin.getEmailId());
        String emailId = passwordLogin.getEmailId();
        Optional<UserAuth> byEmail = userAuthRepository.findByEmail(emailId);
        if(!byEmail.isPresent()){
            UserAuth userAuth = new UserAuth(emailId,passwordEncoder.encode(passwordLogin.getPassword()));
            UserAuth save = userAuthRepository.save(userAuth);
            LOGGER.info("#UserDetailService Saved user: {}",save);
            return new UserUpdateRs(save.getId(),save.getEmail());
        } else {
            LOGGER.error("#UserDetailService User {} already present",byEmail.get());
            throw new RuntimeException("User Already Exits");
        }
    }

    @Override
    public UserUpdateRs updateUser(UserUpdateRq req) {
        UserAuth user;
        if(req.getId()!=null){
             user = userAuthRepository.getById(req.getId());
        } else {
            user = userAuthRepository.findByEmail(req.getEmailId()).orElseThrow(() ->new UsernameNotFoundException("User Not Found"));
        }
        if (user!=null){
            LOGGER.debug("#UserDetailService Found user {} for updating",user);
            user.setEmail(req.getEmailId());
            user.setActive(req.isActive());
            user.setRoles(req.getRoles());
            LOGGER.info("#UserDetailService Updating user {} with {}",user,req);
            UserAuth save = userAuthRepository.save(user);
            LOGGER.debug("#UserDetailService User {} updated",save);
            return new UserUpdateRs(save);
        } else {
            throw new UsernameNotFoundException("User Not Found in DB");
        }
    }

    @Override
    public UserUpdateRs passwordReset(PasswordReset req) {
        UserAuth user = userAuthRepository.findByEmail(req.getEmailId()).orElseThrow(() -> new RuntimeException("User Not Found"));
        if(passwordEncoder.matches(req.getOldPassword(),user.getPassword())){
            user.setPassword(passwordEncoder.encode(req.getNewPassword()));
            LOGGER.info("#UserDetailService Resetting password for {}",req);
            UserAuth save = userAuthRepository.save(user);
            LOGGER.debug("#UserDetailService Password reset done for {}",save);
            return new UserUpdateRs(save);
        } else {
            throw new RuntimeException("#UserDetailService Password mismatch");
        }
    }

    @Override
    public String userLogin(PasswordLogin passwordLogin){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(passwordLogin.getEmailId(),passwordLogin.getPassword())
            );
            LOGGER.debug("#UserDetailService  Setting Authentication context: {}",authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
        UserDetails userDetails = loginUserDetailsService.loadUserByUsername(passwordLogin.getEmailId());
        return jwtHelper.generateToken(userDetails);
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(this.loginUserDetailsService);
        return provider;
    }
}
