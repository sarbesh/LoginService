package com.sarbesh.loginservice.dto;

public class PasswordLogin {
    private String emailId;
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
