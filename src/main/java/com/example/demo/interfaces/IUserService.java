package com.example.demo.interfaces;

import com.example.demo.dto.user.LoginRequest;
import com.example.demo.dto.user.LoginResponse;
import com.example.demo.dto.user.RegisterRequest;
import com.example.demo.dto.user.RegisterResponse;
import com.example.demo.model.User;

import java.util.Optional;

public interface IUserService {
    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);
    User getMe(String username);
}
