package org.example.restservice.user.repository;

import org.example.restservice.user.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Integer> {

    UserEntity findUserEntityById(int userId);
    UserEntity findUserEntityByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}
