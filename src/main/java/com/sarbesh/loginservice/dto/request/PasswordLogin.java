package com.sarbesh.loginservice.dto.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PasswordLogin {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @NotEmpty
    @Email
    private String emailId;

    @NotEmpty
    private String password;

    public PasswordLogin() {
    }

    public PasswordLogin(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    @Override
    public String toString() {
        return "PasswordLogin{" +
                "emailId='" + emailId + '\'' +
                '}';
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
