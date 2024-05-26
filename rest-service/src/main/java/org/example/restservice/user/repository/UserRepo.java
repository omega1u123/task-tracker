package org.example.restservice.user.repository;

import org.example.restservice.user.exception.EntityNotFoundException;
import org.example.restservice.user.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Integer> {

    UserEntity findUserEntityById(int userId);
    UserEntity findUserEntityByEmail(String email);
    UserEntity findUserEntityByUsername(String username);
}
