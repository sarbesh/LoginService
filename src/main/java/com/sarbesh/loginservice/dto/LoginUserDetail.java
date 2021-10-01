package com.sarbesh.loginservice.dto;

import com.sarbesh.loginservice.model.UserAuth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginUserDetail implements UserDetails {
    private String email;
    private String password;
    private Set<GrantedAuthority> roles;
    private boolean active;

    public LoginUserDetail() {
    }

    public LoginUserDetail(UserAuth userAuth) {
        this.email=userAuth.getEmail();
        this.password=userAuth.getPassword();
        this.active= userAuth.isActive();
        this.roles = Stream.of(userAuth.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
