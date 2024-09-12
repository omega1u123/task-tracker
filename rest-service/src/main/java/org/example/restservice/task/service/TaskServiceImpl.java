package org.example.restservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restservice.task.exception.*;
import org.example.restservice.task.model.TaskEntity;
import org.example.restservice.task.controller.payload.EditTaskRequest;
import org.example.restservice.task.model.TaskMapper;
import org.example.restservice.task.model.dto.AddCommentModel;
import org.example.restservice.task.model.dto.CreateTaskModel;
import org.example.restservice.task.model.dto.TaskDTO;
import org.example.restservice.board.repository.BoardRepo;
import org.example.restservice.task.repository.TaskRepo;
import org.example.restservice.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService{

    private final TaskRepo taskRepo;
    private final BoardRepo boardRepo;
    private final TaskMapper taskMapper;

    @Override
    public void createTask(CreateTaskModel task, int boardId, String username) {
        TaskEntity taskEntity = new TaskEntity(
                task.title(),
                task.description(),
                task.status(),
                Timestamp.valueOf(LocalDateTime.now()),
                username,
                boardRepo.findById(boardId).orElseThrow(UserNotFoundException::new)
        );
        try{
            taskRepo.save(taskEntity);
        }catch (RuntimeException e){
            throw new TaskNotCreatedException();
        }

    }

    @Override
    public TaskDTO getTask(int taskId) {
        return taskMapper.mapTaskEntityToDto(taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @Override
    @Transactional
    public TaskDTO changeStatusToCompleted(int taskId) {
        TaskEntity task = taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        task.setStatus("completed");
        task.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
        try {
            taskRepo.save(task);
        }catch (RuntimeException e){
            throw new StatusNotChangedException();
        }
        return taskMapper.mapTaskEntityToDto(task);
    }

    @Override
    @Transactional
    public TaskDTO changeStatusToToDo(int taskId) {
        TaskEntity task = taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        task.setStatus("to do");
        task.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
        try {
            taskRepo.save(task);
        }catch (RuntimeException e){
            throw new StatusNotChangedException();
        }
        return taskMapper.mapTaskEntityToDto(task);
    }

    @Override
    @Transactional
    public TaskDTO changeStatusToInProgress(int taskId) {
        TaskEntity task = taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        task.setStatus("in progress");
        task.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
        try {
            taskRepo.save(task);
        }catch (RuntimeException e){
            throw new StatusNotChangedException();
        }
        return taskMapper.mapTaskEntityToDto(task);
    }

    @Override
    @Transactional
    public TaskDTO editTask(int taskId, EditTaskRequest task) {
        TaskEntity taskEntity = taskRepo.findById(taskId).orElseThrow(TaskNotFoundException::new);
        taskEntity.setTitle(task.title());
        taskEntity.setDescription(task.description());
        taskEntity.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
        try {
            taskRepo.save(taskEntity);
        }catch (RuntimeException e){
            throw new TaskNotEditedException();
        }
        return taskMapper.mapTaskEntityToDto(taskEntity);
    }

    @Override
    @Transactional
    public void deleteTask(int taskId) {
        try{
            taskRepo.deleteById(taskId);
        }catch (RuntimeException e){
            throw new TaskNotDeletedException();
        }
    }

    @Override
    public void addComment(AddCommentModel comment) {

    }

    @Override
    public void deleteComment(int commentId) {

    }
}
