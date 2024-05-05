package com.example.demo.dto.user;

public class RegisterResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegisterResponse(String token) {
        this.token = token;
    }
}
