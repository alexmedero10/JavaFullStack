package com.fullstackalex.TaskManagementSystem.service;

import com.fullstackalex.TaskManagementSystem.dto.AdminUpdateUserRequest;
import com.fullstackalex.TaskManagementSystem.dto.ChangePasswordRequest;
import com.fullstackalex.TaskManagementSystem.dto.UpdateUserRequest;
import com.fullstackalex.TaskManagementSystem.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserByUsername(String username);
    UserResponse updateUser(String username, UpdateUserRequest updateRequest);
    void changePassword(String username, ChangePasswordRequest changePasswordRequest);

    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse adminUpdateUser(Long id, AdminUpdateUserRequest updateRequest);
    void deleteUser(Long id);
}
