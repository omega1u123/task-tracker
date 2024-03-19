package org.example.restservice.user.service;

import org.example.restservice.task.model.dto.TaskDTO;
import org.example.restservice.user.model.dto.UserDTO;

import java.util.List;

public interface UserService {
     UserDTO getUser(int id);

     void createUser(UserDTO user);

     void deleteUser(int id);

     List<TaskDTO> getAllTasks(int userId);

}
