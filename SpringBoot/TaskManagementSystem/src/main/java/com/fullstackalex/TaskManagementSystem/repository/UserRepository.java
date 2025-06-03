package com.fullstackalex.TaskManagementSystem.repository;

import com.fullstackalex.TaskManagementSystem.dto.AdminDashboardResponse;
import com.fullstackalex.TaskManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT u.username as username, COUNT(t.id) as taskCount " +
            "FROM User u LEFT JOIN u.tasks t " +
            "GROUP BY u.username")
    List<AdminDashboardResponse.UserTaskCount> findAllUserTaskCounts();
}
