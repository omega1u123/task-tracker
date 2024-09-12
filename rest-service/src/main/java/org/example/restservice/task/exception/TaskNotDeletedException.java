package org.example.restservice.task.exception;

public class TaskNotDeletedException extends RuntimeException{
    public TaskNotDeletedException(){
        super("task not deleted");
    }
}
