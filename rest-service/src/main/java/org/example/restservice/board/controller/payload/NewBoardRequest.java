package org.example.restservice.board.controller.payload;

public record NewBoardRequest(
        String title,
        int userId
) {
}
