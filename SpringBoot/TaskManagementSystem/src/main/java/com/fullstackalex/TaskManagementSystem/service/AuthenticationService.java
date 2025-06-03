package com.fullstackalex.TaskManagementSystem.service;

import com.fullstackalex.TaskManagementSystem.dto.AuthenticationRequest;
import com.fullstackalex.TaskManagementSystem.dto.AuthenticationResponse;
import com.fullstackalex.TaskManagementSystem.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse refreshToken(HttpServletRequest request);
}
