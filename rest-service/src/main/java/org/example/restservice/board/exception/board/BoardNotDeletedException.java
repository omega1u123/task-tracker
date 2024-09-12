package org.example.restservice.board.exception.board;

public class BoardNotDeletedException extends RuntimeException{
    public BoardNotDeletedException(){
        super("board not deleted");
    }
}
