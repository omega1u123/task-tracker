package org.example.restservice.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restservice.board.exception.board.BoardNotCreatedException;
import org.example.restservice.board.exception.board.BoardNotDeletedException;
import org.example.restservice.board.exception.board.BoardNotFoundException;
import org.example.restservice.board.exception.board.BoardTitleNotEditedException;
import org.example.restservice.board.exception.status.StatusNotCreatedException;
import org.example.restservice.board.exception.status.StatusNotDeletedException;
import org.example.restservice.board.exception.status.StatusNotEditedException;
import org.example.restservice.board.model.BoardEntity;
import org.example.restservice.board.model.BoardMapper;
import org.example.restservice.board.model.StatusEntity;
import org.example.restservice.board.model.dto.BoardDTO;
import org.example.restservice.board.repository.BoardRepo;
import org.example.restservice.board.repository.StatusRepo;
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
    private final StatusRepo statusRepo;
    private final BoardMapper boardMapper;

    private final List<StatusEntity> defaultStatuses = List.of(
            new StatusEntity("to do"),
            new StatusEntity("in progress"),
            new StatusEntity("done")
    );

    @Override
    public BoardDTO createBoard(String title, int userId) {

        List<UserEntity> user = Collections.singletonList(userRepo.findUserEntityById(userId));

        var board = new BoardEntity(
                title,
                defaultStatuses,
                user
        );

        log.info("created board: {}", board);
        try {
            boardRepo.save(board);
            statusRepo.saveAll(defaultStatuses.stream()
                    .peek(s -> s.setBoard(board))
                    .toList());
        }catch (RuntimeException e){
            throw new BoardNotCreatedException();
        }
        return boardMapper.mapEntityToDTO(board);
    }

    @Override
    public BoardDTO getBoard(int boardId) {
        return boardMapper.mapEntityToDTO(boardRepo.findById(boardId).orElseThrow(BoardNotFoundException::new));
    }

    @Override
    public List<BoardDTO> getBoardsByUserId(int userId) {
        return List.of(
                boardMapper.mapEntityToDTO(
                        boardRepo.findBoardEntitiesByUsers(
                                userRepo.findUserEntityById(userId)
                        ).iterator().next()
                ));
    }


    @Override
    @Transactional
    public BoardDTO editBoardTitle(int boardId, String newTitle) {
        var board = boardRepo.findById(boardId).orElseThrow(BoardNotFoundException::new);
        board.setTitle(newTitle);
        try {
            boardRepo.save(board);
        }catch (RuntimeException e){
            throw new BoardTitleNotEditedException();
        }
        return boardMapper.mapEntityToDTO(board);
    }

    @Override
    public void deleteBoard(int boardId) {
        try {
            boardRepo.deleteById(boardId);
        }catch (RuntimeException e){
            throw new BoardNotDeletedException();
        }
    }

    @Override
    @Transactional
    public BoardDTO addStatus(int boardId, String statusName) {
        log.info("statusName: {}", statusName);
        var board = boardRepo.findById(boardId).orElseThrow(BoardNotFoundException::new);
        var status = new StatusEntity(statusName, board);
        try {
            statusRepo.save(status);
        }catch (RuntimeException e){
            throw new StatusNotCreatedException();
        }
        board.getStatuses().add(status);
        return boardMapper.mapEntityToDTO(board);
    }

    @Override
    @Transactional
    public BoardDTO deleteStatus(int boardId, String statusName) {
        var board = boardRepo.findById(boardId).orElseThrow(EntityNotFoundException::new);
        var status = statusRepo.findByBoardAndName(board, statusName);
        board.getStatuses().remove(status);
        try {
            statusRepo.delete(status);
        }catch (RuntimeException e){
            throw new StatusNotDeletedException();
        }
        return boardMapper.mapEntityToDTO(board);
    }

    @Override
    @Transactional
    public BoardDTO editStatus(int boardID, String status, String newStatusName) {
        var board = boardRepo.findById(boardID).orElseThrow(EntityNotFoundException::new);
        int index = board.getStatuses().indexOf(new StatusEntity(status));
        board.getStatuses().set(index, new StatusEntity(newStatusName));
        try {
            boardRepo.save(board);
        }catch (RuntimeException e){
            throw new StatusNotEditedException();
        }
        return boardMapper.mapEntityToDTO(board);
    }

    @Override
    @Transactional
    public BoardDTO addUserToBoard(int boardId, String username) {
        var board = boardRepo.findById(boardId).orElseThrow(EntityNotFoundException::new);
        board.getUsers().add(userRepo.findUserEntityByUsername(username));
        boardRepo.save(board);
        return boardMapper.mapEntityToDTO(board);
    }

    @Override
    @Transactional
    public BoardDTO deleteUserFromBoard(int boardId, String username) {
        var board = boardRepo.findById(boardId).orElseThrow(EntityNotFoundException::new);
        board.getUsers().remove(userRepo.findUserEntityByUsername(username));
        boardRepo.save(board);
        return boardMapper.mapEntityToDTO(board);
    }


}
