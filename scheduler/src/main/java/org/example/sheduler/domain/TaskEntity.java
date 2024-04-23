package org.example.sheduler.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "c_status")
    private String status;

    @Column(name = "c_modified")
    private Timestamp modified;

    @ManyToOne
    @JoinColumn(name = "c_user_id")
    private UserEntity userEntity;

    public TaskEntity(String title, String description, String status, Timestamp modified, UserEntity userEntity) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.modified = modified;
        this.userEntity = userEntity;
    }


}
