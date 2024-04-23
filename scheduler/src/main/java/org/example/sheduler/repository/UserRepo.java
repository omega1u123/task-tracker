package org.example.sheduler.repository;


import org.example.sheduler.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Integer> {
    UserEntity findUserEntityByEmail(String email);
}
