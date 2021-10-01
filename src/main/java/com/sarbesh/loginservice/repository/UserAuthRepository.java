package com.sarbesh.loginservice.repository;

import com.sarbesh.loginservice.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    public Optional<UserAuth> findByEmail(String email);
}
