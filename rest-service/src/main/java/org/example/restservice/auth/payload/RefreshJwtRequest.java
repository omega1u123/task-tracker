package org.example.restservice.auth.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshJwtRequest {

    private String refreshToken;

}
