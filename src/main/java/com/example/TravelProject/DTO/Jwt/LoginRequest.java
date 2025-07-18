package com.example.TravelProject.DTO.Jwt;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
}
