package org.example.restservice.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.restservice.comment.controller.payload.CreateCommentPayload;
import org.example.restservice.comment.controller.payload.EditCommentPayload;
import org.example.restservice.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/task")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{taskId:\\d}/comment/create")
    public ResponseEntity<?> createComment(@RequestBody CreateCommentPayload createCommentPayload){
        try {
            return ResponseEntity.ok(commentService.addComment(
                    createCommentPayload.taskId(),
                    createCommentPayload.username(),
                    createCommentPayload.text()
            ));
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{taskId:\\d}/comment/getComments")
    public ResponseEntity<?> getComments(@PathVariable int taskId){
        try {
            return ResponseEntity.ok(commentService.getComments(taskId));
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{taskId:\\d}/comment/{commentId:\\d}/editComment")
    public ResponseEntity<?> editComment(@PathVariable int commentId, @RequestBody EditCommentPayload editCommentPayload){
        try {
            return ResponseEntity.ok(commentService.editComment(commentId, editCommentPayload.text()));
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{taskId:\\d}/comment/{commentId:\\d}/deleteComment")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId){
        try {
            commentService.deleteComment(commentId);
            return ResponseEntity.ok().build();
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
