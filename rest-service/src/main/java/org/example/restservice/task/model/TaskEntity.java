package org.example.restservice.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.restservice.user.model.UserEntity;

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
    private String title;

    @Column(name = "c_description")
    private String description;

    @Column(name = "c_is_competed")
    private Boolean isCompleted;

    @Column(name = "c_modified")
    private Timestamp modified;

    @ManyToOne
    @JoinColumn(name = "c_user_id")
    private UserEntity userEntity;
}
