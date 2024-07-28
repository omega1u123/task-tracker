package org.example.restservice.task.controller.payload;

public record NewTaskRequest(
        String title,
        String description,
        String status,
        int boardId,
        String username
) {
}
