package org.example.restservice.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.restservice.user.model.UserEntity;

@Data
@AllArgsConstructor
public class UserDTO {

    public Integer id;
    public String email;
    public String username;

    public static UserDTO mapUserToDTO(UserEntity userEntity){
        return new UserDTO(userEntity.getId(),userEntity.getEmail(), userEntity.getUsername());
    }

}
