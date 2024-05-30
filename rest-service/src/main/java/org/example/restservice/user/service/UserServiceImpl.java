package org.example.restservice.user.service;

import lombok.AllArgsConstructor;
import org.example.restservice.board.model.dto.BoardDTO;
import org.example.restservice.board.repository.BoardRepo;
import org.example.restservice.board.service.BoardService;
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
    private final BoardRepo boardRepo;
    private final BoardService boardService;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public UserDTO getUser(int id) {
        return UserDTO.mapUserToDTO(userRepo.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    @Transactional
    public UserDTO createUser(UserEntity user) {
        userRepo.save(user);
        //rabbitTemplate.convertAndSend("taskExchange", "new-user", user.getEmail());
        return UserDTO.mapUserToDTO(user);
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    @Transactional
    public List<BoardDTO> getAllBoards(int userId) {
        return boardService.getBoardsByUserId(userId);
    }
}
