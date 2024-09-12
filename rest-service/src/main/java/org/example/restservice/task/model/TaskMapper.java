package org.example.restservice.task.model;

import org.example.restservice.task.model.dto.TaskDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO mapTaskEntityToDto(TaskEntity taskEntity){
        return new TaskDTO(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getStatus(),
                taskEntity.getModifiedAt(),
                taskEntity.getModifiedBy()
        );
    }

}
