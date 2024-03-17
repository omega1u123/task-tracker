package org.example.restservice.user.service;

import lombok.AllArgsConstructor;
import org.example.restservice.user.exception.EntityNotFoundException;
import org.example.restservice.user.model.UserEntity;
import org.example.restservice.user.model.dto.UserDTO;
import org.example.restservice.user.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    @Override
    public UserDTO getUser(int id) {
        return UserDTO.mapUserToDTO(userRepo.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    @Transactional
    public void createUser(UserDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userRepo.save(userEntity);
    }

    @Override
    public void deleteUser(int id) {

    }
}
