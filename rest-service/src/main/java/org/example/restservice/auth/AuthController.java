package org.example.restservice.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restservice.auth.payload.JwtRequest;
import org.example.restservice.auth.payload.RefreshJwtRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest){
        try{
            var response = authService.authenticate(jwtRequest);
            return ResponseEntity.ok(response);
        }catch (UsernameNotFoundException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/refreshAccessToken")
    public ResponseEntity<?> refreshAccessToken(@RequestBody RefreshJwtRequest refreshToken){
        try{
            log.info("refresh token controller");
            var response = authService.refreshAccessToken(refreshToken);
            return ResponseEntity.ok(response);
        }catch (UsernameNotFoundException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }



}
