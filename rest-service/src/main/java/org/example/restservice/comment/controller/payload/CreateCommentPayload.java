package org.example.restservice.comment.controller.payload;

public record CreateCommentPayload(
        String text,
        String username,
        int taskId
) {
}
