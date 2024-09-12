package org.example.restservice.board.exception.board;

public class BoardNotFoundException extends RuntimeException{
    public BoardNotFoundException(){
        super("book not found");
    }
}
