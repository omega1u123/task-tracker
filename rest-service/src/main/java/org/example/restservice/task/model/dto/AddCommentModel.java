package org.example.restservice.task.model.dto;

import lombok.Data;

@Data
public class AddCommentModel {

    private int taskId;
    private String username;
    private String text;

}
