package org.example.restservice.task.service;

import org.example.restservice.task.model.dto.EditTaskRequest;
import org.example.restservice.task.model.dto.TaskDTO;

public interface TaskService {

    void createTask(TaskDTO task, int userId);

    TaskDTO getTask(int taskId);

    void completeTask(int taskId);

    void editTask(int taskId, EditTaskRequest task);

}
