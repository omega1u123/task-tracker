package org.example.restservice.comment.exception;

public class CommentNotCreatedException extends RuntimeException{
    public CommentNotCreatedException(){
        super("comment not created");
    }
}
