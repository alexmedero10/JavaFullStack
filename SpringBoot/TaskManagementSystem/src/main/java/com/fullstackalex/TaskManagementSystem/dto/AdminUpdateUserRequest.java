package com.fullstackalex.TaskManagementSystem.dto;

import com.fullstackalex.TaskManagementSystem.model.enums.Role;
import lombok.Data;

@Data
public class AdminUpdateUserRequest {
    private Role role;
    private Boolean locked;
}
