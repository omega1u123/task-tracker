package org.example.restservice.task.controller;

import org.example.restservice.task.exception.StatusNotChangedException;
import org.example.restservice.task.exception.TaskNotDeletedException;
import org.example.restservice.task.exception.TaskNotEditedException;
import org.example.restservice.task.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskControllerExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> taskNotFoundHandler(TaskNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

    @ExceptionHandler(StatusNotChangedException.class)
    public ResponseEntity<String> statusNotChangedHandler(StatusNotChangedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

    @ExceptionHandler(TaskNotEditedException.class)
    public ResponseEntity<String> taskNotEditedHandler(TaskNotEditedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

    @ExceptionHandler(TaskNotDeletedException.class)
    public ResponseEntity<String> taskNotDeletedHandler(TaskNotDeletedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

}
