package org.example.restservice.user.service;

import org.example.restservice.user.model.dto.UserDTO;

public interface UserService {
     UserDTO getUser(int id);

     void createUser(UserDTO user);

     void deleteUser(int id);

}
