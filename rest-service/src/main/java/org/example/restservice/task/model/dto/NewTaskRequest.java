package org.example.restservice.task.model.dto;

import lombok.Data;

@Data
public class NewTaskRequest {

    private String title;

    private String description;

    private int userId;

}
