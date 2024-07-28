package org.example.restservice.board.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.restservice.task.model.TaskEntity;
import org.example.restservice.user.model.UserEntity;

import java.util.List;

@Data
@ToString(exclude = {"statuses", "users", "tasks"})
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

    @OneToMany(mappedBy = "board")
    private List<StatusEntity> statuses;

    @ManyToMany
    @JoinTable(
            name = "t_board_users",
            joinColumns = @JoinColumn(name = "c_board_id"),
            inverseJoinColumns = @JoinColumn(name = "c_user_id")
    )
    private List<UserEntity> users;

    @OneToMany(mappedBy = "board")
    private List<TaskEntity> tasks;

    public BoardEntity(String title, List<StatusEntity> statuses, List<UserEntity> users) {
        this.title = title;
        this.statuses = statuses;
        this.users = users;
    }
}
