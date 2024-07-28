package org.example.restservice.board.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "c_board_id")
    private BoardEntity board;

    public StatusEntity(String name) {
        this.name = name;
    }

    public StatusEntity(String name, BoardEntity board) {
        this.name = name;
        this.board = board;
    }
}
