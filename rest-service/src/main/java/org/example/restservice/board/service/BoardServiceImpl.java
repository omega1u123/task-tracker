package org.example.restservice.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restservice.board.model.BoardEntity;
import org.example.restservice.board.model.dto.BoardDTO;
import org.example.restservice.board.repository.BoardRepo;
import org.example.restservice.user.exception.EntityNotFoundException;
import org.example.restservice.user.model.UserEntity;
import org.example.restservice.user.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{

    private final UserRepo userRepo;
    private final BoardRepo boardRepo;

    @Override
    public BoardDTO createBoard(String title, int userId) {

        Set<UserEntity> user = Collections.singleton(userRepo.findUserEntityById(userId));

        var board = new BoardEntity(
                title,
                new ArrayList<>(Arrays.asList("to do", "in process", "completed")),
                user
        );

        log.info("created board: {}", board);

        boardRepo.save(board);

        return BoardDTO.mapEntityToDTO(board);
    }

    @Override
    public BoardDTO getBoard(int boardId) {
        return BoardDTO.mapEntityToDTO(boardRepo.findById(boardId).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<BoardDTO> getBoardsByUserId(int userId) {
        return List.of(BoardDTO.mapEntityToDTO(boardRepo.findBoardEntitiesByUsers(userRepo.findUserEntityById(userId)).iterator().next()));
    }


    @Override
    @Transactional
    public BoardDTO editBoardTitle(int boardId, String newTitle) {
        var board = boardRepo.findById(boardId).orElseThrow(EntityNotFoundException::new);
        board.setTitle(newTitle);
        boardRepo.save(board);
        return BoardDTO.mapEntityToDTO(board);
    }

    @Override
    public void deleteBoard(int boardId) {
        boardRepo.deleteById(boardId);
    }

    @Override
    @Transactional
    public BoardDTO addStatus(int boardId, String status) {
        var board = boardRepo.findById(boardId).orElseThrow(EntityNotFoundException::new);
        board.getStatuses().add(status);
        boardRepo.save(board);
        return BoardDTO.mapEntityToDTO(board);
    }

    @Override
    @Transactional
    public BoardDTO deleteStatus(int boardId, String status) {
        var board = boardRepo.findById(boardId).orElseThrow(EntityNotFoundException::new);
        board.getStatuses().remove(status);
        boardRepo.save(board);
        return BoardDTO.mapEntityToDTO(board);
    }

    @Override
    @Transactional
    public BoardDTO editStatus(int boardID, String status, String newStatus) {
        var board = boardRepo.findById(boardID).orElseThrow(EntityNotFoundException::new);
        int index = board.getStatuses().indexOf(status);
        board.getStatuses().set(index, newStatus);
        boardRepo.save(board);
        return BoardDTO.mapEntityToDTO(board);
    }

    @Override
    @Transactional
    public BoardDTO addUserToBoard(int boardId, String username) {
        var board = boardRepo.findById(boardId).orElseThrow(EntityNotFoundException::new);
        board.getUsers().add(userRepo.findUserEntityByUsername(username));
        boardRepo.save(board);
        return BoardDTO.mapEntityToDTO(board);
    }

    @Override
    @Transactional
    public BoardDTO deleteUserFromBoard(int boardId, String username) {
        var board = boardRepo.findById(boardId).orElseThrow(EntityNotFoundException::new);
        board.getUsers().remove(userRepo.findUserEntityByUsername(username));
        boardRepo.save(board);
        return BoardDTO.mapEntityToDTO(board);
    }


}
