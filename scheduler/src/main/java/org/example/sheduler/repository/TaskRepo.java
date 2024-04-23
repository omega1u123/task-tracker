package org.example.sheduler.repository;

import org.example.sheduler.domain.TaskEntity;
import org.example.sheduler.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends CrudRepository<TaskEntity, Integer> {

    List<TaskEntity> findAllByUserEntity(UserEntity userEntity);
    List<TaskEntity> findAllByUserEntityAndStatus(UserEntity userEntity, String status);


}
