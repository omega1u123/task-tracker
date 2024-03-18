package org.example.restservice.task.service;

import org.example.restservice.task.model.dto.TaskDTO;

public interface TaskService {

    void createTask(TaskDTO task, int userId);

    TaskDTO getTask(int taskId);

}
