package com.fullstackalex.TaskManagementSystem.repository;

import com.fullstackalex.TaskManagementSystem.model.Task;
import com.fullstackalex.TaskManagementSystem.model.User;
import com.fullstackalex.TaskManagementSystem.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    List<Task> findByUserId(Long userId);

    @Query("SELECT t FROM Task t WHERE t.dueDate BETWEEN :start AND :end AND t.status <> :status")
    List<Task> findByDueDateBetweenAndStatusNot(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("status") TaskStatus status);

    Optional<Task> findByIdAndUserUsername(Long id, String username);

    long countByUser(User user);

    long countByStatus(TaskStatus status);

    void deleteAllByUserId(Long userId);
}
