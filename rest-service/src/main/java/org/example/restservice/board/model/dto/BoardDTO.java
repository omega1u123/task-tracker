package org.example.restservice.board.model.dto;

import org.example.restservice.task.model.dto.TaskDTO;
import org.example.restservice.user.model.dto.UserDTO;

import java.util.List;


public record BoardDTO(

        int id,

        String title,

        List<String> statuses,

        List<UserDTO> users,

        List<TaskDTO> tasks
) {
}
