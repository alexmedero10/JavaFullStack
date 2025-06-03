package com.fullstackalex.TaskManagementSystem.service;

import com.fullstackalex.TaskManagementSystem.dto.AdminDashboardResponse;
import com.fullstackalex.TaskManagementSystem.dto.AdminUpdateUserRequest;
import com.fullstackalex.TaskManagementSystem.dto.TaskResponse;
import com.fullstackalex.TaskManagementSystem.dto.UserResponse;
import com.fullstackalex.TaskManagementSystem.exception.AccessDeniedException;
import com.fullstackalex.TaskManagementSystem.exception.OperationNotAllowedException;
import com.fullstackalex.TaskManagementSystem.exception.ResourceNotFoundException;
import com.fullstackalex.TaskManagementSystem.model.User;
import com.fullstackalex.TaskManagementSystem.model.enums.TaskStatus;
import com.fullstackalex.TaskManagementSystem.repository.TaskRepository;
import com.fullstackalex.TaskManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Transactional
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    // User Management
    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList();
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> modelMapper.map(task, TaskResponse.class))
                .toList();
    }

    @Override
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new ResourceNotFoundException("Task", "id", taskId);
        }
        taskRepository.deleteById(taskId);
    }

    // Dashboard
    @Override
    @Transactional(readOnly = true)
    public AdminDashboardResponse getDashboard() {
        long totalUsers = userRepository.count();
        long totalTasks = taskRepository.count();
        long pendingTasks = taskRepository.countByStatus(TaskStatus.PENDING);
        long completedTasks = taskRepository.countByStatus(TaskStatus.COMPLETED);

        List<AdminDashboardResponse.UserTaskCount> userTaskCounts = userRepository.findAllUserTaskCounts()
                .stream()
                .map(projection -> new AdminDashboardResponse.UserTaskCount(
                        projection.getUsername(),
                        projection.getTaskCount()))
                .toList();

        return AdminDashboardResponse.builder()
                .totalUsers(totalUsers)
                .totalTasks(totalTasks)
                .pendingTasks(pendingTasks)
                .completedTasks(completedTasks)
                .userTaskCounts(userTaskCounts)
                .build();
    }
}
