package org.example.restservice.comment.service;

import org.example.restservice.comment.model.CommentEntity;

import java.util.List;

public interface CommentService {
    List<CommentEntity> getComments(int taskId);
    CommentEntity addComment(int taskId, String username, String text);
    void deleteComment(int commentId);
    CommentEntity editComment(int commentId, String text);
}
