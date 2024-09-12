package org.example.restservice.comment.exception;

public class CommentNotDeletedException extends RuntimeException{
    public CommentNotDeletedException(){
        super("comment not deleted");
    }
}
