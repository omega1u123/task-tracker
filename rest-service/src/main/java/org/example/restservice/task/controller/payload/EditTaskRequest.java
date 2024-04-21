package org.example.restservice.task.controller.payload;

import lombok.Data;

@Data
public class EditTaskRequest {
    private String title;
    private String description;
}
