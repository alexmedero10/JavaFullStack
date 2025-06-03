package com.fullstackalex.TaskManagementSystem.controller;

import com.fullstackalex.TaskManagementSystem.dto.AuthenticationRequest;
import com.fullstackalex.TaskManagementSystem.dto.AuthenticationResponse;
import com.fullstackalex.TaskManagementSystem.dto.RegisterRequest;
import com.fullstackalex.TaskManagementSystem.exception.OperationNotAllowedException;
import com.fullstackalex.TaskManagementSystem.model.enums.Role;
import com.fullstackalex.TaskManagementSystem.service.AuthenticationService;
import com.fullstackalex.TaskManagementSystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse register(@Valid @RequestBody RegisterRequest request) {
        if (request.getRole() == Role.ROLE_ADMIN) {
            throw new OperationNotAllowedException("No se puede registrar como ADMIN");
        }

        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/refresh-token")
    public AuthenticationResponse refreshToken(HttpServletRequest request) {
        return authenticationService.refreshToken(request);
    }
}
