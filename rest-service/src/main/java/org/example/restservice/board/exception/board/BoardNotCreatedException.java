package org.example.restservice.board.exception.board;

public class BoardNotCreatedException extends RuntimeException{
    public BoardNotCreatedException(){
        super("board not created");
    }
}
