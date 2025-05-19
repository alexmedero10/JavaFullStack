package com.fullstackalex.TaskManagement.repositories;

import com.fullstackalex.TaskManagement.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
