package org.example.restservice.task.exception;

public class TaskNotCreatedException extends RuntimeException{
    public TaskNotCreatedException(){
        super("task not created");
    }
}
