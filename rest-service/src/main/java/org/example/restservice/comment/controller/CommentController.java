package org.example.restservice.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.restservice.comment.controller.payload.CreateCommentPayload;
import org.example.restservice.comment.controller.payload.EditCommentPayload;
import org.example.restservice.comment.model.dto.CommentDto;
import org.example.restservice.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment/")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("{taskId:\\d+}/getComments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable int taskId){
        return ResponseEntity.ok(commentService.getComments(taskId));
    }

    @PostMapping("create")
    public ResponseEntity<CommentDto> createComment(@RequestBody CreateCommentPayload createCommentPayload){
        return ResponseEntity.ok(commentService.addComment(
                            createCommentPayload.taskId(),
                            createCommentPayload.username(),
                            createCommentPayload.text()
                    ));
    }

    @PutMapping("{commentId:\\d}/editComment")
    public ResponseEntity<CommentDto> editComment(@PathVariable int commentId, @RequestBody EditCommentPayload editCommentPayload){
        return ResponseEntity.ok(commentService.editComment(commentId, editCommentPayload.text()));
    }

    @DeleteMapping("{commentId:\\d}/deleteComment")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable int commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
