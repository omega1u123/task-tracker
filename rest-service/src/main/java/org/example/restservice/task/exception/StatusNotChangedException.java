package org.example.restservice.task.exception;

public class StatusNotChangedException extends RuntimeException{
    public StatusNotChangedException(){
        super("status not changed");
    }
}
