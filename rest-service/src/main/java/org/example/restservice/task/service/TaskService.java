package org.example.restservice.task.service;

import org.example.restservice.task.controller.payload.EditTaskRequest;
import org.example.restservice.task.model.dto.TaskDTO;

public interface TaskService {

    void createTask(TaskDTO task, int userId);
    TaskDTO getTask(int taskId);
    void changeStatusToCompleted(int taskId);
    void changeStatusToToDo(int taskId);
    void changeStatusToInProgress(int taskId);
    void editTask(int taskId, EditTaskRequest task);
    void deleteTask(int taskId);

}
