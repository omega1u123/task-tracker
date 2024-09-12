package org.example.restservice.comment.service;

import org.example.restservice.comment.model.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getComments(int taskId);
    CommentDto addComment(int taskId, String username, String text);
    void deleteComment(int commentId);
    CommentDto editComment(int commentId, String text);
}
