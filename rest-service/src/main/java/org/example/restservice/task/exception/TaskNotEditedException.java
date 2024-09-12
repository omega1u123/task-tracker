package org.example.restservice.task.exception;

public class TaskNotEditedException extends RuntimeException{
    public TaskNotEditedException(){
        super("task not edited");
    }
}
