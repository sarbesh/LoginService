package com.sarbesh.loginservice.controller;

import com.sarbesh.loginservice.dto.response.LoginResponse;
import com.sarbesh.loginservice.dto.request.PasswordLogin;
import com.sarbesh.loginservice.dto.response.UserUpdateRs;
import com.sarbesh.loginservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/prelogin/auth")
public class LoginApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginApiController.class);

    @Autowired
    private UserService userService;


    @PostMapping("/passwordLogin")
    public ResponseEntity<?> passwordLogin(@Valid @RequestBody PasswordLogin passwordLogin) throws AuthenticationException {
        LOGGER.debug("#LoginApiController Received Login request for {}",passwordLogin);
        String jwtToken = userService.userLogin(passwordLogin);
        LOGGER.info("#LoginApiController passwordLogin returning token for {}",passwordLogin);
        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody PasswordLogin passwordLogin){
        LOGGER.info("#LoginApiController Registering user: {}",passwordLogin);
        UserUpdateRs userUpdateRs = userService.registerUser(passwordLogin);
        LOGGER.debug("#LoginApiController Registered user: {}",userUpdateRs);
        return ResponseEntity.ok(userUpdateRs);
    }
}
