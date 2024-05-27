package org.example.restservice.board.service;


import org.example.restservice.board.model.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    BoardDTO createBoard(String title, int userId);
    BoardDTO getBoard(int boardId);
    List<BoardDTO> getBoardsByUserId(int userId);
    BoardDTO editBoardTitle(int boardId, String newTitle);
    void deleteBoard(int boardId);
    BoardDTO addStatus(int boardId, String status);
    BoardDTO deleteStatus(int boardId, String status);
    BoardDTO editStatus(int boardId, String status, String newStatus);
    BoardDTO addUserToBoard(int boardId, String username);
    BoardDTO deleteUserFromBoard(int boardId, String username);

}
