package org.example.restservice.user.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("user not found");
    }
}
