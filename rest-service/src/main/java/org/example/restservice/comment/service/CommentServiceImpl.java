package org.example.restservice.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restservice.comment.exception.CommentNotCreatedException;
import org.example.restservice.comment.exception.CommentNotDeletedException;
import org.example.restservice.comment.exception.CommentNotEditedException;
import org.example.restservice.comment.exception.CommentNotFoundException;
import org.example.restservice.comment.model.CommentEntity;
import org.example.restservice.comment.model.dto.CommentDto;
import org.example.restservice.comment.repository.CommentRepo;
import org.example.restservice.task.exception.TaskNotFoundException;
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
        var task = taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        List<CommentEntity> comments = commentRepo.findAllByTask(task);
        return comments.stream()
                .map(com -> new CommentDto(com.getText(), com.getUsername()))
                .toList();
    }

    @Transactional
    @Override
    public CommentDto addComment(int taskId, String username, String text) {
        var task = taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        var comment = new CommentEntity(username, text, task);
        try {
            commentRepo.save(comment);
        }catch (RuntimeException e){
            throw new CommentNotCreatedException();
        }
        var commentDto = new CommentDto(text, username);
        log.info("add comment resp: {}", commentDto);
        return commentDto;
    }

    @Transactional
    @Override
    public void deleteComment(int commentId) {
        var comment = commentRepo.findById(commentId).orElseThrow(CommentNotFoundException::new);
        try {
            commentRepo.delete(comment);
        }catch (RuntimeException e){
            throw new CommentNotDeletedException();
        }
    }

    @Transactional
    @Override
    public CommentDto editComment(int commentId, String text) {
        var comment = commentRepo.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.setText(text);
        try{
            commentRepo.save(comment);
        }catch (RuntimeException e){
            throw new CommentNotEditedException();
        }
        return new CommentDto(comment.getText(), comment.getUsername());
    }
}
