package org.example.restservice.task.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.restservice.task.model.TaskEntity;

import java.sql.Timestamp;

@AllArgsConstructor
@Data
public class TaskDTO {

    private int id;

    private String title;

    private String description;

    private String status;

    private Timestamp modified;

    public TaskDTO(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public static TaskDTO mapTaskToDTO(TaskEntity taskEntity){
        return new TaskDTO(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getDescription(), taskEntity.getStatus(), taskEntity.getModified());
    }



}
