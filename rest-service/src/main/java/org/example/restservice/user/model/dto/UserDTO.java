package org.example.restservice.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.restservice.user.model.UserEntity;

@Data
@AllArgsConstructor
public class UserDTO {

    public String email;
    public String password;

    public static UserDTO mapUserToDTO(UserEntity userEntity){
        return new UserDTO(userEntity.getEmail(), userEntity.getPassword());
    }

}
