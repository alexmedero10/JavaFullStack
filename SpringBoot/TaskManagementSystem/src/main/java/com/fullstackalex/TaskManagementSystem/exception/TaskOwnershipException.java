package com.fullstackalex.TaskManagementSystem.exception;

public class TaskOwnershipException extends RuntimeException {
    public TaskOwnershipException(Long taskId) {
        super("You don't have permission to modify task with ID: " + taskId);
    }
}
