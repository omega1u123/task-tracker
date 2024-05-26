package org.example.restservice.task.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.restservice.board.model.BoardEntity;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_task")
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_title")
    @NotNull
    private String title;

    @Column(name = "c_description")
    private String description;

    @Column(name = "c_status")
    @NotNull
    private String status;

    @Column(name = "c_modified_at")
    @NotNull
    private Timestamp modifiedAt;

    @Column(name = "c_modified_by")
    @NotNull
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "c_board_id")
    @NotNull
    private BoardEntity boardEntity;

    public TaskEntity(String title, String description, String status, Timestamp modified, BoardEntity boardEntity) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.modifiedAt = modified;
        this.boardEntity = boardEntity;
    }

    public TaskEntity(String title, String description, String status, BoardEntity boardEntity) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.boardEntity = boardEntity;
    }
}
