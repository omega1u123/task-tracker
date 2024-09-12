package org.example.restservice.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restservice.comment.model.CommentEntity;
import org.example.restservice.comment.model.dto.CommentDto;
import org.example.restservice.comment.repository.CommentRepo;
import org.example.restservice.task.repository.TaskRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService{

    private final CommentRepo commentRepo;
    private final TaskRepo taskRepo;

    @Override
    public List<CommentDto> getComments(int taskId) {
        var task = taskRepo.findById(taskId).orElseThrow();
        List<CommentEntity> comments = commentRepo.findAllByTask(task);
        return comments.stream()
                .map(com -> new CommentDto(com.getText(), com.getUsername()))
                .toList();
    }

    @Transactional
    @Override
    public CommentDto addComment(int taskId, String username, String text) {
        var task = taskRepo.findById(taskId).orElseThrow();
        var comment = new CommentEntity(username, text, task);
        commentRepo.save(comment);
        var commentDto = new CommentDto(text, username);
        log.info("add comment resp: {}", commentDto);
        return commentDto;
    }

    @Transactional
    @Override
    public void deleteComment(int commentId) {
        var comment = commentRepo.findById(commentId).orElseThrow();
        commentRepo.delete(comment);
    }

    @Transactional
    @Override
    public CommentDto editComment(int commentId, String text) {
        var comment = commentRepo.findById(commentId).orElseThrow();
        comment.setText(text);
        commentRepo.save(comment);
        var commentDto = new CommentDto(comment.getText(), comment.getUsername());
        return commentDto;
    }
}
