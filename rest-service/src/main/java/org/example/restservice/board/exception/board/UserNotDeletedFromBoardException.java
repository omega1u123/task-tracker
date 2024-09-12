package org.example.restservice.board.exception.board;

public class UserNotDeletedFromBoardException extends RuntimeException{
    public UserNotDeletedFromBoardException(){
        super("user not deleted form board");
    }
}
