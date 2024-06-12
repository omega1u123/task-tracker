package org.example.restservice.task.service;

import org.example.restservice.task.controller.payload.EditTaskRequest;
import org.example.restservice.task.model.dto.AddCommentModel;
import org.example.restservice.task.model.dto.CreateTaskModel;
import org.example.restservice.task.model.dto.TaskDTO;

public interface TaskService {

    void createTask(CreateTaskModel task, int boardId, String username);
    TaskDTO getTask(int taskId);
    void changeStatusToCompleted(int taskId);
    void changeStatusToToDo(int taskId);
    void changeStatusToInProgress(int taskId);
    void editTask(int taskId, EditTaskRequest task);
    void deleteTask(int taskId);
    void addComment(AddCommentModel comment);
    void deleteComment(int commentId);

}
