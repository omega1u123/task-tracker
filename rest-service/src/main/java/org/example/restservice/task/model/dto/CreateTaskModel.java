package org.example.restservice.task.model.dto;

public record CreateTaskModel(
        String title,
        String description,
        String status,
        int boardId
) {
}
