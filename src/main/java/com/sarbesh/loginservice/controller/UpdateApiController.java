package com.sarbesh.loginservice.controller;

import com.sarbesh.loginservice.dto.request.PasswordReset;
import com.sarbesh.loginservice.dto.request.UserUpdateRq;
import com.sarbesh.loginservice.dto.response.UserUpdateRs;
import com.sarbesh.loginservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class UpdateApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateApiController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/passwordReset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody PasswordReset passwordReset){
        LOGGER.info("#UpdateApiController Received password reset for {}",passwordReset.getEmailId());
        UserUpdateRs userUpdateRs = userService.passwordReset(passwordReset);
        LOGGER.debug("Password reset done for {}",userUpdateRs.getEmail());
        return ResponseEntity.ok(userUpdateRs);
    }

    @PostMapping("/userUpdate")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateRq rq){
        LOGGER.info("#UpdateApiController Received update request for {}",rq);
        UserUpdateRs userUpdateRs = userService.updateUser(rq);
        LOGGER.debug("User updated: {}",userUpdateRs);
        return ResponseEntity.ok(userUpdateRs);
    }

}
