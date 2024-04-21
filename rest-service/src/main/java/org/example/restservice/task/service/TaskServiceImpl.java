package org.example.restservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restservice.task.exception.TaskNotFoundException;
import org.example.restservice.task.model.TaskEntity;
import org.example.restservice.task.controller.payload.EditTaskRequest;
import org.example.restservice.task.model.dto.TaskDTO;
import org.example.restservice.task.repository.TaskRepo;
import org.example.restservice.user.exception.EntityNotFoundException;
import org.example.restservice.user.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService{

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    @Override
    @Transactional
    public void createTask(TaskDTO task, int userId) {
        TaskEntity taskEntity = new TaskEntity(
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                Timestamp.valueOf(LocalDateTime.now()),
                userRepo.findById(userId).orElseThrow(EntityNotFoundException::new)
        );
        taskRepo.save(taskEntity);
    }

    @Override
    public TaskDTO getTask(int taskId) {
        return TaskDTO.mapTaskToDTO(taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @Override
    @Transactional
    public void changeStatusToCompleted(int taskId) {
        TaskEntity task = taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        task.setStatus("completed");
        task.setModified(Timestamp.valueOf(LocalDateTime.now()));
        taskRepo.save(task);
    }

    @Override
    @Transactional
    public void changeStatusToToDo(int taskId) {
        TaskEntity task = taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        task.setStatus("to do");
        task.setModified(Timestamp.valueOf(LocalDateTime.now()));
        taskRepo.save(task);
    }

    @Override
    @Transactional
    public void changeStatusToInProgress(int taskId) {
        TaskEntity task = taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        task.setStatus("in progress");
        task.setModified(Timestamp.valueOf(LocalDateTime.now()));
        taskRepo.save(task);
    }

    @Override
    @Transactional
    public void editTask(int taskId, EditTaskRequest task) {
        TaskEntity taskEntity = taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setModified(Timestamp.valueOf(LocalDateTime.now()));

    }

    @Override
    @Transactional
    public void deleteTask(int taskId) {
        taskRepo.deleteById(taskId);
    }
}
