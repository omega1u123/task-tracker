package org.example.restservice.board.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewBoardRequest {
    private String title;
    private int userId;
}
