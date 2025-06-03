package com.fullstackalex.TaskManagementSystem.service;

import com.fullstackalex.TaskManagementSystem.dto.TaskResponse;
import com.fullstackalex.TaskManagementSystem.model.Task;
import com.fullstackalex.TaskManagementSystem.model.User;
import com.fullstackalex.TaskManagementSystem.model.enums.Role;
import com.fullstackalex.TaskManagementSystem.model.enums.TaskPriority;
import com.fullstackalex.TaskManagementSystem.model.enums.TaskStatus;
import com.fullstackalex.TaskManagementSystem.repository.TaskRepository;
import com.fullstackalex.TaskManagementSystem.repository.UserRepository;
import com.fullstackalex.TaskManagementSystem.utils.AuthenticationFacade;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User testUser;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @BeforeEach
    void setUp() {
        // Crear usuario de prueba
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPassword(passwordEncoder.encode("password"));
        testUser.setRole(Role.ROLE_USER);
        userRepository.save(testUser);

        // Configurar fechas para las pruebas
        startDate = LocalDateTime.now().minusDays(1);
        endDate = LocalDateTime.now().plusDays(1);
    }

    @Test
    void shouldFindTasksByDateRangeAndStatus() {
        // Arrange
        Task completedTask = new Task();
        completedTask.setTitle("Completed Task");
        completedTask.setDescription("This task is completed");
        completedTask.setStatus(TaskStatus.COMPLETED);
        completedTask.setPriority(TaskPriority.MEDIUM);
        completedTask.setDueDate(LocalDateTime.now());
        completedTask.setUser(testUser);

        Task pendingTask = new Task();
        pendingTask.setTitle("Pending Task");
        pendingTask.setDescription("This task is pending");
        pendingTask.setStatus(TaskStatus.PENDING);
        pendingTask.setPriority(TaskPriority.HIGH);
        pendingTask.setDueDate(LocalDateTime.now().plusHours(2));
        pendingTask.setUser(testUser);

        taskRepository.saveAll(List.of(completedTask, pendingTask));

        // Act
        List<Task> results = taskRepository.findByDueDateBetweenAndStatusNot(
                startDate,
                endDate,
                TaskStatus.COMPLETED);

        // Assert
        assertEquals(1, results.size());
        assertEquals(TaskStatus.PENDING, results.get(0).getStatus());
        assertEquals("Pending Task", results.get(0).getTitle());
    }

    @Test
    void shouldNotFindTasksOutsideDateRange() {
        // Arrange
        Task outOfRangeTask = new Task();
        outOfRangeTask.setTitle("Out of range task");
        outOfRangeTask.setStatus(TaskStatus.PENDING);
        outOfRangeTask.setDueDate(LocalDateTime.now().plusDays(2)); // Fuera del rango
        outOfRangeTask.setUser(testUser);
        taskRepository.save(outOfRangeTask);

        // Act
        List<Task> results = taskRepository.findByDueDateBetweenAndStatusNot(
                startDate,
                endDate,
                TaskStatus.COMPLETED);

        // Assert
        assertTrue(results.isEmpty());
    }

    @Test
    void shouldFindOnlyTasksWithCorrectStatus() {
        // Arrange
        Task inProgressTask = new Task();
        inProgressTask.setTitle("In Progress Task");
        inProgressTask.setStatus(TaskStatus.IN_PROGRESS);
        inProgressTask.setDueDate(LocalDateTime.now());
        inProgressTask.setUser(testUser);
        taskRepository.save(inProgressTask);

        // Act
        List<Task> results = taskRepository.findByDueDateBetweenAndStatusNot(
                startDate,
                endDate,
                TaskStatus.COMPLETED);

        // Assert
        assertEquals(1, results.size());
        assertEquals(TaskStatus.IN_PROGRESS, results.get(0).getStatus());
    }

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
        userRepository.deleteAll();
    }
}