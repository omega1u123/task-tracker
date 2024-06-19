package org.example.restservice.board.controller;

import lombok.RequiredArgsConstructor;
import org.example.restservice.board.controller.payload.NewBoardRequest;
import org.example.restservice.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("{boardId:\\d}")
    public ResponseEntity<?> getBoard(@PathVariable("boardId") int boardId){
        try{
            return ResponseEntity.ok(boardService.getBoard(boardId));
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody NewBoardRequest boardRequest){
        try {
            return ResponseEntity.ok(boardService.createBoard(boardRequest.getTitle(), boardRequest.getUserId()));
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().body("invalid input");
        }
    }

    @PutMapping("{boardId:\\d}/changeTitle")
    public ResponseEntity<?> editeTitle(@PathVariable("boardId") int boardId, @RequestBody String newTitle){
        try{
            return ResponseEntity.ok(boardService.editBoardTitle(boardId, newTitle));
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
    }





    @DeleteMapping("{boardId:\\d}")
    public ResponseEntity<?> deleteBoard(@PathVariable("boardId") int boardId){
        try {
            boardService.deleteBoard(boardId);
            return ResponseEntity.ok("board deleted");
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("{boardId:\\d}/addStatus")
    public ResponseEntity<?> addStatus(@PathVariable("boardId") int boardId, @RequestBody String status){
        try{
            return ResponseEntity.ok(boardService.addStatus(boardId, status));
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{boardId:\\d}/deleteStatus")
    public ResponseEntity<?> deleteStatus(@PathVariable("boardId") int boardId, @RequestBody String status){
        try {
            return ResponseEntity.ok(boardService.deleteStatus(boardId, status));
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{boardId:\\d}/editStatus")
    public ResponseEntity<?> editStatus(@PathVariable("boardId") int boardId, @RequestBody String status, @RequestBody String newStatus){
        try {
            return ResponseEntity.ok(boardService.editStatus(boardId, status, newStatus));
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
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
