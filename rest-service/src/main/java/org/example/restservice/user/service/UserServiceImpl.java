package org.example.restservice.user.service;

import lombok.AllArgsConstructor;
import org.example.restservice.task.model.dto.TaskDTO;
import org.example.restservice.task.repository.TaskRepo;
import org.example.restservice.user.exception.EntityNotFoundException;
import org.example.restservice.user.model.UserEntity;
import org.example.restservice.user.model.dto.UserDTO;
import org.example.restservice.user.repository.UserRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final TaskRepo taskRepo;
    private final RabbitTemplate rabbitTemplate;

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
        rabbitTemplate.convertAndSend("taskExchange", "new-user", user.getEmail());
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    @Transactional
    public List<TaskDTO> getAllTasks(int userId) {
        return taskRepo.findAllByUserEntity(userRepo.findById(userId).orElseThrow())
                .stream()
                .map(TaskDTO::mapTaskToDTO)
                .toList();
    }
}
