package org.example.restservice.task.repository;

import org.example.restservice.task.model.TaskEntity;
import org.example.restservice.user.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends CrudRepository<TaskEntity, Integer> {

    List<TaskEntity> findAllByUserEntity(UserEntity userEntity);

}
