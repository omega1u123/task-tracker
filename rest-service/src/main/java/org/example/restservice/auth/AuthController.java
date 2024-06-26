package org.example.restservice.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restservice.auth.payload.JwtRequest;
import org.example.restservice.auth.payload.RefreshJwtRequest;
import org.example.restservice.user.controller.payload.NewUserRequest;
import org.example.restservice.user.model.UserEntity;
import org.example.restservice.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/signIn")
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

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody NewUserRequest user){
        try{
            var newUser = userService.createUser(new UserEntity(user.getEmail(), user.getPassword(), user.getUsername()));
            var response = authService.authenticate(new JwtRequest(user.getEmail(), user.getPassword()));
            return ResponseEntity.ok(List.of(newUser, response));
        }catch (RuntimeException ex){
            log.info("signUp ex: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("creating user failed");
        }
    }


}
