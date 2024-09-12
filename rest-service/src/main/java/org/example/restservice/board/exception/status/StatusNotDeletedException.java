package org.example.restservice.board.exception.status;

public class StatusNotDeletedException extends RuntimeException{
    public StatusNotDeletedException(){
        super("status not deleted");
    }
}
