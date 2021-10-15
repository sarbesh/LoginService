package com.sarbesh.loginservice.dto.response;

import com.sarbesh.loginservice.model.UserAuth;

public class UserUpdateRs {
    private Long id;
    private String email;
    private String roles;
    private boolean active;

    public UserUpdateRs(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public UserUpdateRs(Long id, String email, String roles, boolean active) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.active = active;
    }

    public UserUpdateRs() {
    }

    public UserUpdateRs(UserAuth userAuth) {
        this.id= userAuth.getId();
        this.email= userAuth.getEmail();
        this.active= userAuth.isActive();
        this.roles= userAuth.getRoles();
    }

    @Override
    public String toString() {
        return "UserUpdateRs{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                ", active=" + active +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
