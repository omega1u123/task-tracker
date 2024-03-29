package org.example.restservice.task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restservice.task.exception.TaskNotFoundException;
import org.example.restservice.task.model.dto.NewTaskRequest;
import org.example.restservice.task.model.dto.TaskDTO;
import org.example.restservice.task.service.TaskService;
import org.example.restservice.user.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("task/")
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @GetMapping("{taskId:\\d+}")
    public ResponseEntity<?> getTask(@PathVariable("taskId") int taskId){
        try{
            return ResponseEntity.ok(taskService.getTask(taskId));
        }catch (TaskNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("task ne naiden");
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> createTask(@RequestBody NewTaskRequest task){
        try {
            log.info("task form request : {}", task.toString());
            taskService.createTask(new TaskDTO(task.getTitle(), task.getDescription()), task.getUserId());
            return ResponseEntity.ok("task created");
        }catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
