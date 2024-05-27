package org.example.restservice.user.controller.payload;

import lombok.Getter;

@Getter
public class NewUserRequest {
    private String email;
    private String password;
    private String username;
}
