package com.fullstackalex.TaskManagementSystem.service;

import com.fullstackalex.TaskManagementSystem.dto.AdminDashboardResponse;
import com.fullstackalex.TaskManagementSystem.dto.TaskResponse;
import com.fullstackalex.TaskManagementSystem.dto.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
    List<TaskResponse> getAllTasks();
    void deleteTask(Long id);
    AdminDashboardResponse getDashboard();
}