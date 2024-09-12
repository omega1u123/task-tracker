package org.example.restservice.board.exception.status;

public class StatusNotCreatedException extends RuntimeException{
    public StatusNotCreatedException(){
        super("status not created");
    }
}
