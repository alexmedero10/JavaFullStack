package com.fullstackalex.TaskManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardResponse {
    private long totalUsers;
    private long totalTasks;
    private long pendingTasks;
    private long completedTasks;
    private List<UserTaskCount> userTaskCounts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserTaskCount {
        private String username;
        private long taskCount;
    }
}
