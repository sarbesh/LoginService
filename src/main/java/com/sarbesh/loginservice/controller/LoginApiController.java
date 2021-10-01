package com.sarbesh.loginservice.controller;

import com.sarbesh.core.security.JwtHelper;
import com.sarbesh.loginservice.dto.LoginResponse;
import com.sarbesh.loginservice.dto.PasswordLogin;
import com.sarbesh.loginservice.service.LoginUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginApiController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginUserDetailService loginUserDetailService;

    @Autowired
    private JwtHelper jwtHelper;

    @RequestMapping("/passwordLogin")
    public ResponseEntity<?> passwordLogin(@RequestBody PasswordLogin passwordLogin) throws AuthenticationException {
        LOGGER.debug("#LoginApiController Received Login request for {}",passwordLogin);
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(passwordLogin.getEmailId(),passwordLogin.getPassword())
            );
            LOGGER.debug("#LoginApiController Setting Authentication context: {}",authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
        UserDetails userDetails = loginUserDetailService.loadUserByUsername(passwordLogin.getEmailId());
        String jwtToken = jwtHelper.generateToken(userDetails);
        LOGGER.info("#LoginApiController passwordLogin returning token for {}",passwordLogin);
        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }
}
