package org.example.restservice.board.model;

import org.example.restservice.board.model.dto.BoardDTO;
import org.example.restservice.task.model.TaskMapper;
import org.example.restservice.user.model.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public BoardDTO mapEntityToDTO(BoardEntity board){
        if(board.getTasks() == null){
            return new BoardDTO(
                    board.getId(),
                    board.getTitle(),
                    board.getStatuses().stream()
                            .map(StatusEntity::getName)
                            .toList(),
                    board.getUsers().stream()
                            .map(UserDTO::mapUserToDTO)
                            .toList(),
                    null
            );
        };

        TaskMapper taskMapper = new TaskMapper();

        return new BoardDTO(
                board.getId(),
                board.getTitle(),
                board.getStatuses().stream()
                        .map(StatusEntity::getName)
                        .toList(),
                board.getUsers().stream()
                        .map(UserDTO::mapUserToDTO)
                        .toList(),
                board.getTasks().stream()
                        .map(taskMapper::mapTaskEntityToDto)
                        .toList()
        );
    }
}
