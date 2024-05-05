package com.example.demo.controller;

import com.example.demo.dto.user.LoginRequest;
import com.example.demo.dto.user.LoginResponse;
import com.example.demo.dto.user.RegisterRequest;
import com.example.demo.dto.user.RegisterResponse;
import com.example.demo.interfaces.IUserService;
import com.example.demo.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse result = userService.login(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse token = userService.register(request);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<User> me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(user);
    }

}
