package org.example.restservice.user.service;

import org.example.restservice.board.model.dto.BoardDTO;
import org.example.restservice.task.model.dto.TaskDTO;
import org.example.restservice.user.model.UserEntity;
import org.example.restservice.user.model.dto.UserDTO;

import java.util.List;

public interface UserService {
     UserDTO getUser(int id);

     UserDTO createUser(UserEntity user);

     void deleteUser(int id);

     List<BoardDTO> getAllBoards(int userId);

}
