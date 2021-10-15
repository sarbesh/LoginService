package com.sarbesh.loginservice.service;

import com.sarbesh.loginservice.dto.request.PasswordLogin;
import com.sarbesh.loginservice.dto.request.PasswordReset;
import com.sarbesh.loginservice.dto.request.UserUpdateRq;
import com.sarbesh.loginservice.dto.response.UserUpdateRs;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String userLogin(PasswordLogin passwordLogin);

    UserUpdateRs registerUser(PasswordLogin passwordLogin);

    UserUpdateRs updateUser(UserUpdateRq userUpdateRq);

    UserUpdateRs passwordReset(PasswordReset passwordReset);
}
