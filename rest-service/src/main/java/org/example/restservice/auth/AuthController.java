package org.example.restservice.auth;

import lombok.RequiredArgsConstructor;
import org.example.restservice.auth.payload.JwtRequest;
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
    public ResponseEntity<?> refreshAccessToken(@RequestBody String refreshToken){
        try{
            var response = authService.refreshAccessToken(refreshToken);
            return ResponseEntity.ok(response);
        }catch (UsernameNotFoundException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }



}
