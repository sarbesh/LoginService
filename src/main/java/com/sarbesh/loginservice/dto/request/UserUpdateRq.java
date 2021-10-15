package com.sarbesh.loginservice.dto.request;

import javax.validation.constraints.NotEmpty;

public class UserUpdateRq {
    private Long id;
    private String emailId;
    @NotEmpty
    private String roles;
    private boolean active=false;

    @Override
    public String toString() {
        return "UserUpdateRq{" +
                "id=" + id +
                ", emailId='" + emailId + '\'' +
                ", roles='" + roles + '\'' +
                ", active=" + active +
                '}';
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
