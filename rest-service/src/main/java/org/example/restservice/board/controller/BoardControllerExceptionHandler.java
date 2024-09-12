package org.example.restservice.board.controller;

import org.example.restservice.board.exception.board.BoardNotCreatedException;
import org.example.restservice.board.exception.board.BoardNotDeletedException;
import org.example.restservice.board.exception.board.BoardNotFoundException;
import org.example.restservice.board.exception.board.BoardTitleNotEditedException;
import org.example.restservice.board.exception.status.StatusNotCreatedException;
import org.example.restservice.board.exception.status.StatusNotDeletedException;
import org.example.restservice.board.exception.status.StatusNotEditedException;
import org.example.restservice.user.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BoardControllerExceptionHandler {

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<String> boardNotFoundHandler(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(BoardNotCreatedException.class)
    public ResponseEntity<String> boardNotCreatedHandler(BoardNotCreatedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

    @ExceptionHandler(BoardTitleNotEditedException.class)
    public ResponseEntity<String> boardTitleNotEditedException(BoardTitleNotEditedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

    @ExceptionHandler(BoardNotDeletedException.class)
    public ResponseEntity<String> boardNotDeletedHandler(BoardNotDeletedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

    @ExceptionHandler(StatusNotCreatedException.class)
    public ResponseEntity<String> statusNotCreatedHandler(StatusNotCreatedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

    @ExceptionHandler(StatusNotDeletedException.class)
    public ResponseEntity<String> statusNotDeletedHandler(StatusNotDeletedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

    @ExceptionHandler(StatusNotEditedException.class)
    public ResponseEntity<String> statusNotEditedHandler(StatusNotEditedException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(e.getMessage());
    }

}
