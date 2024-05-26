package org.example.restservice.task.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class CreateTaskModel {

    private String title;

    private String description;

    private String status;

    private int boardId;
}
