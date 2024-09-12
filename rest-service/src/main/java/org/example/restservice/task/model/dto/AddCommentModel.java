package org.example.restservice.task.model.dto;

public record AddCommentModel(
        int taskId,
        String username,
        String text
) {
}
