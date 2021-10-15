package com.sarbesh.loginservice.dto.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PasswordReset {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @NotEmpty
    @Email
    private String emailId;
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;

    @Override
    public String toString() {
        return "PasswordReset{" +
                "emailId='" + emailId + '\'' +
                '}';
    }

    public PasswordReset() {
    }

    public PasswordReset(String emailId, String oldPassword, String newPassword) {
        this.emailId = emailId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
