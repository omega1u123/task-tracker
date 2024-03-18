package org.example.restservice.task.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.restservice.task.model.TaskEntity;

@AllArgsConstructor
@Data
public class TaskDTO {

    private String title;

    private String description;


    public static TaskDTO mapTaskToDTO(TaskEntity taskEntity){
        return new TaskDTO(taskEntity.getTitle(), taskEntity.getDescription());
    }

}
