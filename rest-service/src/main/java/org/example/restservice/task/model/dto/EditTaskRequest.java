package org.example.restservice.task.model.dto;

import lombok.Data;

@Data
public class EditTaskRequest {
    private String title;
    private String description;
}
