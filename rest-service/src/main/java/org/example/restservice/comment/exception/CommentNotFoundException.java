package org.example.restservice.comment.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(){
        super("comment not found");
    }
}
