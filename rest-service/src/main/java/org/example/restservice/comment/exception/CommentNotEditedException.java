package org.example.restservice.comment.exception;

public class CommentNotEditedException extends RuntimeException{
    public CommentNotEditedException() {
        super("comment not edited");
    }
}
