package com.fullstackalex.TaskManagementSystem.service;

import com.fullstackalex.TaskManagementSystem.dto.TaskRequest;
import com.fullstackalex.TaskManagementSystem.dto.TaskResponse;
import com.fullstackalex.TaskManagementSystem.exception.ResourceNotFoundException;
import com.fullstackalex.TaskManagementSystem.model.Task;
import com.fullstackalex.TaskManagementSystem.model.User;
import com.fullstackalex.TaskManagementSystem.model.enums.TaskPriority;
import com.fullstackalex.TaskManagementSystem.model.enums.TaskStatus;
import com.fullstackalex.TaskManagementSystem.repository.TaskRepository;
import com.fullstackalex.TaskManagementSystem.repository.UserRepository;
import com.fullstackalex.TaskManagementSystem.utils.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    private final ModelMapper modelMapper;

    @Override
    public Page<TaskResponse> getAllTasks(TaskStatus status, TaskPriority priority, LocalDateTime dueDate,
                                          int page, int size, String[] sort) {
        String username = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Sort sorting = Sort.by(sort[0]);
        if (sort.length > 1 && sort[1].equalsIgnoreCase("desc")) {
            sorting = sorting.descending();
        } else {
            sorting = sorting.ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sorting);

        Specification<Task> spec = Specification.where((root, query, cb) ->
                cb.equal(root.get("user"), user));

        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }

        if (priority != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("priority"), priority));
        }

        if (dueDate != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("dueDate"), dueDate));
        }

        return taskRepository.findAll(spec, pageable)
                .map(task -> modelMapper.map(task, TaskResponse.class));
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        String username = authenticationFacade.getAuthentication().getName();
        Task task = taskRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        return modelMapper.map(task, TaskResponse.class);
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        logger.info("CREANDO TAREA...");
        String username = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        Task task = modelMapper.map(taskRequest, Task.class);
        task.setUser(user);
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);
        logger.info("TAREA CREADA...");
        return modelMapper.map(savedTask, TaskResponse.class);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        String username = authenticationFacade.getAuthentication().getName();
        Task existingTask = taskRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        // Actualiza solo los campos permitidos
        existingTask.setTitle(taskRequest.getTitle());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setStatus(taskRequest.getStatus());
        existingTask.setPriority(taskRequest.getPriority());
        existingTask.setDueDate(taskRequest.getDueDate());
        existingTask.setUpdatedAt(LocalDateTime.now());

        Task updatedTask = taskRepository.save(existingTask);
        return modelMapper.map(updatedTask, TaskResponse.class);
    }

    @Override
    public void deleteTask(Long id) {
        String username = authenticationFacade.getAuthentication().getName();
        Task task = taskRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        taskRepository.delete(task);
    }
}
