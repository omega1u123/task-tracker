package org.example.restservice.user.controller;

import lombok.AllArgsConstructor;
import org.example.restservice.user.controller.payload.NewUserRequest;
import org.example.restservice.user.exception.EntityNotFoundException;
import org.example.restservice.user.model.UserEntity;
import org.example.restservice.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("user/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @GetMapping("{userId:\\d+}")
    public ResponseEntity<?> getUser(@PathVariable("userId") int id){
        try{
           return ResponseEntity.ok().body(userService.getUser(id));
        }catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody NewUserRequest user){
        try{
            userService.createUser(new UserEntity(user.getEmail(), user.getPassword(), user.getUsername()));
            return ResponseEntity.ok().build();
        }catch (Exception exc){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("this email is already registered");
        }
    }

    @GetMapping("{userId:\\d+}/tasks")
    public ResponseEntity<?> getBoards(@PathVariable("userId") int userId){
        try {
            return ResponseEntity.ok(userService.getAllBoards(userId));
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("oops");
        }
    }


}
