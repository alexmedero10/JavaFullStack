package com.fullstackalex.TaskManagementSystem.dto;

import com.fullstackalex.TaskManagementSystem.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String username;
    private Role role;
    private LocalDateTime expiresAt;
}
