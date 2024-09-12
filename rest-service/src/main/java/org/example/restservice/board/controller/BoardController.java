package org.example.restservice.board.controller;

import lombok.RequiredArgsConstructor;
import org.example.restservice.board.controller.payload.NewBoardRequest;
import org.example.restservice.board.controller.payload.NewStatusPayload;
import org.example.restservice.board.controller.payload.NewTitlePayload;
import org.example.restservice.board.model.dto.BoardDTO;
import org.example.restservice.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("{boardId:\\d}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable("boardId") int boardId){
        return ResponseEntity.ok(boardService.getBoard(boardId));
    }

    @PostMapping
    public ResponseEntity<BoardDTO> createBoard(@RequestBody NewBoardRequest boardRequest){
        return ResponseEntity.ok(boardService.createBoard(boardRequest.title(), boardRequest.userId()));
    }

    @PutMapping("{boardId:\\d}/changeTitle")
    public ResponseEntity<BoardDTO> editeTitle(@PathVariable("boardId") int boardId, @RequestBody NewTitlePayload newTitlePayload){
        return ResponseEntity.ok(boardService.editBoardTitle(boardId, newTitlePayload.newTitle()));
    }


    @DeleteMapping("{boardId:\\d}")
    public ResponseEntity<String> deleteBoard(@PathVariable("boardId") int boardId){
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok("board deleted");
    }

    @PostMapping("{boardId:\\d}/addStatus")
    public ResponseEntity<BoardDTO> addStatus(@PathVariable("boardId") int boardId, @RequestBody NewStatusPayload newStatusPayload){
        return ResponseEntity.ok(boardService.addStatus(boardId, newStatusPayload.statusName()));
    }

    @DeleteMapping("{boardId:\\d}/deleteStatus")
    public ResponseEntity<BoardDTO> deleteStatus(@PathVariable("boardId") int boardId, @RequestBody String status){
        return ResponseEntity.ok(boardService.deleteStatus(boardId, status));
    }

    @PutMapping("{boardId:\\d}/editStatus")
    public ResponseEntity<BoardDTO> editStatus(@PathVariable("boardId") int boardId, @RequestBody String status, @RequestBody String newStatus){
        return ResponseEntity.ok(boardService.editStatus(boardId, status, newStatus));
    }

    @PutMapping("{boardId:\\d}/addUser")
    public ResponseEntity<?> addUser(@PathVariable("boardId") int boardId, @RequestBody String username){
        try{
            return ResponseEntity.ok(boardService.addUserToBoard(boardId, username));
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{boardId:\\d}/deleteUser")
    public ResponseEntity<?> deleteUser(@PathVariable("boardId") int boardId, @RequestBody String username){
        try{
            return ResponseEntity.ok(boardService.deleteUserFromBoard(boardId, username));
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
    }

}
