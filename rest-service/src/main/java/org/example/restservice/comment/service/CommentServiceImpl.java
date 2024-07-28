package org.example.restservice.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.restservice.comment.model.CommentEntity;
import org.example.restservice.comment.repository.CommentRepo;
import org.example.restservice.task.repository.TaskRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepo commentRepo;
    private final TaskRepo taskRepo;

    @Override
    public List<CommentEntity> getComments(int taskId) {
        var task = taskRepo.findById(taskId).orElseThrow();
        return commentRepo.findAllByTask(task);
    }

    @Transactional
    @Override
    public CommentEntity addComment(int taskId, String username, String text) {
        var task = taskRepo.findById(taskId).orElseThrow();
        var comment = new CommentEntity(username, text, task);
        commentRepo.save(comment);
        return comment;
    }

    @Transactional
    @Override
    public void deleteComment(int commentId) {
        var comment = commentRepo.findById(commentId).orElseThrow();
        commentRepo.delete(comment);
    }

    @Transactional
    @Override
    public CommentEntity editComment(int commentId, String text) {
        var comment = commentRepo.findById(commentId).orElseThrow();
        comment.setText(text);
        commentRepo.save(comment);
        return comment;
    }
}
