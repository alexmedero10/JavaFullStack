package com.fullstackalex.TaskManagementSystem.dto;

import com.fullstackalex.TaskManagementSystem.model.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;
}
