package org.example.restservice.task.controller.payload;

import lombok.Data;

@Data
public class NewTaskRequest {

    private String title;

    private String description;

    private String status;

    private int userId;

}
