package org.example.restservice.comment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.restservice.task.model.TaskEntity;

@Entity
@Table(name = "t_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_username")
    private String username;

    @Column(name = "c_text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "c_task_id")
    private TaskEntity task;

    public CommentEntity(String username, String text, TaskEntity task) {
        this.username = username;
        this.text = text;
        this.task = task;
    }
}
