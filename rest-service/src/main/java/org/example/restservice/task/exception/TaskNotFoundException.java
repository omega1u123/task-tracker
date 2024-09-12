package org.example.restservice.task.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(){
        super("task not found");
    }
}
