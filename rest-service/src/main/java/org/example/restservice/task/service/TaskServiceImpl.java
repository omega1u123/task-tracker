package org.example.restservice.task.service;

import lombok.RequiredArgsConstructor;
import org.example.restservice.task.exception.TaskNotFoundException;
import org.example.restservice.task.model.TaskEntity;
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
public class TaskServiceImpl implements TaskService{

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    @Override
    @Transactional
    public void createTask(TaskDTO task, int userId) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setIsCompleted(false);
        taskEntity.setModified(Timestamp.valueOf(LocalDateTime.now()));
        taskEntity.setUserEntity(userRepo.findById(userId).orElseThrow(EntityNotFoundException::new));
        taskRepo.save(taskEntity);
    }

    @Override
    public TaskDTO getTask(int taskId) {
        return TaskDTO.mapTaskToDTO(taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new));
    }
}
