package org.example.restservice.task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restservice.task.exception.TaskNotFoundException;
import org.example.restservice.task.controller.payload.EditTaskRequest;
import org.example.restservice.task.controller.payload.NewTaskRequest;
import org.example.restservice.task.model.dto.CreateTaskModel;
import org.example.restservice.task.model.dto.TaskDTO;
import org.example.restservice.task.service.TaskService;
import org.example.restservice.user.exception.UserNotFoundException;
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
        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    @PostMapping("create")
    public ResponseEntity<String> createTask(@RequestBody NewTaskRequest task){
        log.info("task form request : {}", task.toString());
            taskService.createTask(
                    new CreateTaskModel(
                            task.title(),
                            task.description(),
                            task.status(),
                            task.boardId()
                    ),
                    task.boardId(),
                    task.username()
            );
            return ResponseEntity.ok("task created");
    }

    @PatchMapping("{taskId:\\d+}/changeStatusToCompleted")
    public ResponseEntity<TaskDTO> changeStatusToCompleted(@PathVariable("taskId") int taskId){
        return ResponseEntity.ok(taskService.changeStatusToCompleted(taskId));
    }

    @PatchMapping("{taskId:\\d+}/changeStatusToToDo")
    public ResponseEntity<TaskDTO> changeStatusToToDo(@PathVariable("taskId") int taskId){
        return ResponseEntity.ok( taskService.changeStatusToToDo(taskId));
    }

    @PatchMapping("{taskId:\\d+}/changeStatusToInProgress")
    public ResponseEntity<TaskDTO> changeStatusToInProgress(@PathVariable("taskId") int taskId){
        return ResponseEntity.ok(taskService.changeStatusToInProgress(taskId));
    }

    @PutMapping("{taskId:\\d+}/editTask")
    public ResponseEntity<TaskDTO> editTask(@PathVariable("taskId") int taskId,@RequestBody EditTaskRequest task){
        log.info(task.toString());
        taskService.editTask(taskId, task);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{taskId:\\d+}/deleteTask")
    public ResponseEntity<?> deleteTask(@PathVariable("taskId") int taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().body("task deleted");
    }

}
