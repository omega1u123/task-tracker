package org.example.restservice.board.exception.status;

public class StatusNotEditedException extends RuntimeException{
    public StatusNotEditedException(){
        super("status not edited");
    }
}
