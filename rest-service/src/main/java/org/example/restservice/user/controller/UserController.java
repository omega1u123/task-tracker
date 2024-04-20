package org.example.restservice.user.controller;

import lombok.AllArgsConstructor;
import org.example.restservice.user.exception.EntityNotFoundException;
import org.example.restservice.user.model.dto.UserDTO;
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
    public ResponseEntity<?> createUser(@RequestBody UserDTO user){
        try{
            userService.createUser(user);
            return ResponseEntity.ok().build();
        }catch (Exception exc){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("this email is already registered");
        }
    }

    @GetMapping("{userId:\\d+}/tasks")
    public ResponseEntity<?> getUserTasks(@PathVariable("userId") int userId){
        try {
            return ResponseEntity.ok(userService.getAllTasks(userId));
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("oops");
        }
    }


}
