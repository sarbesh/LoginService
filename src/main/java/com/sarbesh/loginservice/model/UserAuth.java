package com.sarbesh.loginservice.model;

import javax.persistence.*;

@Entity
@Table(name = "UserAuth", indexes = {
        @Index(name = "idx_userauth_email_unq", columnList = "email", unique = true)
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_userauth_email_id", columnNames = {"id", "email"})
})
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String roles;
    private boolean active=false;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "UserAuth{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                ", active=" + active +
                '}';
    }

    public UserAuth() {
    }

    public UserAuth(Long id, String email, String password, String roles, boolean active) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public UserAuth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserAuth(Long id, String roles, boolean active) {
        this.id = id;
        this.roles = roles;
        this.active = active;
    }
}
