package com.fullstackalex.TaskManagementSystem.service;

import com.fullstackalex.TaskManagementSystem.dto.AuthenticationRequest;
import com.fullstackalex.TaskManagementSystem.dto.AuthenticationResponse;
import com.fullstackalex.TaskManagementSystem.dto.RegisterRequest;
import com.fullstackalex.TaskManagementSystem.exception.DuplicateResourceException;
import com.fullstackalex.TaskManagementSystem.exception.InvalidTokenException;
import com.fullstackalex.TaskManagementSystem.model.User;
import com.fullstackalex.TaskManagementSystem.repository.UserRepository;
import com.fullstackalex.TaskManagementSystem.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username ya está en uso");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email ya está registrado");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user.getUsername());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user.getUsername());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException("Token de refresco inválido");
        }

        final String refreshToken = authHeader.substring(7);
        final String username = jwtService.extractUsername(refreshToken);

        if (username != null) {
            User user = userRepository.findByUsername(username)
                    .orElseThrow();

            if (jwtService.validateToken(refreshToken)) {
                var accessToken = jwtService.generateToken(user.getUsername());
                return AuthenticationResponse.builder()
                        .token(accessToken)
                        .username(user.getUsername())
                        .role(user.getRole())
                        .build();
            }
        }
        throw new InvalidTokenException("Token de refresco expirado o inválido");
    }
}
