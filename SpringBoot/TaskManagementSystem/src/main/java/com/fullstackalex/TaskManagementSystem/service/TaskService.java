package com.fullstackalex.TaskManagementSystem.service;

import com.fullstackalex.TaskManagementSystem.dto.TaskRequest;
import com.fullstackalex.TaskManagementSystem.dto.TaskResponse;
import com.fullstackalex.TaskManagementSystem.model.enums.TaskPriority;
import com.fullstackalex.TaskManagementSystem.model.enums.TaskStatus;
import org.springframework.data.domain.Page;


import java.time.LocalDateTime;

public interface TaskService {
    Page<TaskResponse> getAllTasks(TaskStatus status, TaskPriority priority, LocalDateTime dueDate,
                                   int page, int size, String[] sort);
    TaskResponse getTaskById(Long id);
    TaskResponse createTask(TaskRequest taskRequest);
    TaskResponse updateTask(Long id, TaskRequest taskRequest);
    void deleteTask(Long id);

}
