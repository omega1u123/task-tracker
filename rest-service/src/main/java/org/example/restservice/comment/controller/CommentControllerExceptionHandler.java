package org.example.restservice.comment.controller;

import org.example.restservice.comment.exception.CommentNotCreatedException;
import org.example.restservice.comment.exception.CommentNotDeletedException;
import org.example.restservice.comment.exception.CommentNotEditedException;
import org.example.restservice.comment.exception.CommentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentControllerExceptionHandler {

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<String> commentNotFoundHandler(CommentNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

    @ExceptionHandler(CommentNotCreatedException.class)
    public ResponseEntity<String> commentNotCreatedHandler(CommentNotCreatedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

    @ExceptionHandler(CommentNotDeletedException.class)
    public ResponseEntity<String> commentNotDeletedHandler(CommentNotDeletedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

    @ExceptionHandler(CommentNotEditedException.class)
    public ResponseEntity<String> commentNotEditedHandler(CommentNotEditedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

}
