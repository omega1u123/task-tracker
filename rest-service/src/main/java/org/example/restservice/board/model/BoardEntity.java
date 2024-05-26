package org.example.restservice.board.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.restservice.user.model.UserEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_title")
    private String title;

    @ElementCollection
    @Column(name = "c_statuses")
    private List<String> statuses;

    @ManyToMany
    @Column(name = "c_user_id")
    private List<UserEntity> users;


    public BoardEntity(String title, List<String> statuses, List<UserEntity> users) {
        this.title = title;
        this.statuses = statuses;
        this.users = users;
    }
}
