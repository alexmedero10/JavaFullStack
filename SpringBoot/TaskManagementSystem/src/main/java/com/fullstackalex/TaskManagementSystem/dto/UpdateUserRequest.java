package com.fullstackalex.TaskManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @Email
    private String email;

    @Size(min = 2, max = 50)
    private String fullName;
}
