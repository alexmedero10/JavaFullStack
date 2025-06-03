package com.fullstackalex.TaskManagementSystem.controller;

import com.fullstackalex.TaskManagementSystem.dto.ChangePasswordRequest;
import com.fullstackalex.TaskManagementSystem.dto.UpdateUserRequest;
import com.fullstackalex.TaskManagementSystem.dto.UserResponse;
import com.fullstackalex.TaskManagementSystem.service.UserService;
import com.fullstackalex.TaskManagementSystem.utils.AuthenticationFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationFacade authenticationFacade;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        String username = authenticationFacade.getAuthentication().getName();
        UserResponse userResponse = userService.getUserByUsername(username);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateCurrentUser(
            @Valid @RequestBody UpdateUserRequest updateRequest) {
        String username = authenticationFacade.getAuthentication().getName();
        UserResponse updatedUser = userService.updateUser(username, updateRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/me/change-password")
    public ResponseEntity<Void> changePassword(
            @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        String username = authenticationFacade.getAuthentication().getName();
        userService.changePassword(username, changePasswordRequest);
        return ResponseEntity.noContent().build();
    }
}
