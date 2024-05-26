package org.example.restservice.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_email")
    @NotNull
    private String email;

    @Column(name = "c_password")
    @NotNull
    private String password;

    @Column(name = "c_username")
    @NotNull
    private String username;

}
