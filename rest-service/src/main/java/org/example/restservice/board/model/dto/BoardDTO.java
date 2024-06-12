package org.example.restservice.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.restservice.board.model.BoardEntity;
import org.example.restservice.task.model.dto.TaskDTO;
import org.example.restservice.user.model.dto.UserDTO;

import java.util.List;

@Data
@AllArgsConstructor
public class BoardDTO {

    private int id;

    private String title;

    private List<String> statuses;

    private List<UserDTO> users;

    private List<TaskDTO> tasks;

    public static BoardDTO mapEntityToDTO(BoardEntity entity){
        return new BoardDTO(
                entity.getId(), entity.getTitle(), entity.getStatuses(),
                entity.getUsers().stream()
                    .map(UserDTO::mapUserToDTO)
                    .toList(),
                entity.getTasks().stream()
                        .map(TaskDTO::mapTaskToDTO)
                        .toList()
        );
    }

}
