package org.example.restservice.board.exception.board;

public class BoardTitleNotEditedException extends RuntimeException{
    public BoardTitleNotEditedException(){
        super("board title not edited");
    }
}
