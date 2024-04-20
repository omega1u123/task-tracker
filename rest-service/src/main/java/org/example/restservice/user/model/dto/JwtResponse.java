package org.example.restservice.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {

    private final String type = "bearer";
    private String refreshToken;
    private String accessToken;

}
