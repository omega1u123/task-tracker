package org.example.restservice.board.exception.board;

public class UserNotAddedToBoardException extends RuntimeException{
    public UserNotAddedToBoardException(){
        super("user not added to board");
    }
}
