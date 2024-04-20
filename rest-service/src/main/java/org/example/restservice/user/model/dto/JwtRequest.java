package org.example.restservice.user.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {

    private String email;
    private String password;

}
