package org.example.restservice.task.model.comment;

import jakarta.persistence.*;
import lombok.Data;
import org.example.restservice.task.model.TaskEntity;


@Entity
@Data
@Table(name = "t_comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "c_task_id")
    private TaskEntity task;

    @Column(name = "c_username")
    private String username;

    @Column(name = "c_text")
    private String text;


}
