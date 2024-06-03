package org.example.restservice.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.restservice.board.model.BoardEntity;

import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(mappedBy = "users")
    private Set<BoardEntity> boards;

    public UserEntity(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
