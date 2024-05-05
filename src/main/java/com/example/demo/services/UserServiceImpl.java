package com.example.demo.services;

import com.example.demo.common.exceptions.InvalidUserException;
import com.example.demo.dto.user.LoginRequest;
import com.example.demo.dto.user.LoginResponse;
import com.example.demo.dto.user.RegisterRequest;
import com.example.demo.dto.user.RegisterResponse;
import com.example.demo.exceptions.InvalidLoginException;
import com.example.demo.interfaces.IJwtService;
import com.example.demo.interfaces.IUserService;
import com.example.demo.model.User;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private IJwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public LoginResponse login(LoginRequest request) {
        User data = this.userRepository.findByUsername(request.getUsername());

        if (data == null || !passwordEncoder.matches(request.getPassword(), data.getPassword())) {
            throw new InvalidUserException("invalid username or password");
        }

        String token = jwtService.generateToken(data);
        return new LoginResponse(token);
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Create new user
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setRole("admin");

        // Save user
        userRepository.save(newUser);

        return new RegisterResponse(jwtService.generateToken(newUser));
    }

    @Override
    public User getMe(String username) {
        return userRepository.findByUsername(username);
    }

}
