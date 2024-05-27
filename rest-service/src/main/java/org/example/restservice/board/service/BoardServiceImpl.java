package org.example.restservice.board.service;

import lombok.RequiredArgsConstructor;
import org.example.restservice.board.model.BoardEntity;
import org.example.restservice.board.model.dto.BoardDTO;
import org.example.restservice.board.repository.BoardRepo;
import org.example.restservice.user.exception.EntityNotFoundException;
import org.example.restservice.user.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final UserRepo userRepo;
    private final BoardRepo boardRepo;

    @Override
    public BoardDTO createBoard(String title, int userId) {

        var board = new BoardEntity(
                title,
                new ArrayList<>(Arrays.asList("to do", "in process", "completed")),
                new ArrayList<>(Collections.singletonList(userRepo.findUserEntityById(userId)))
        );

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
