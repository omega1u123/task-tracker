package org.example.restservice.task.repository;

import org.example.restservice.board.model.BoardEntity;
import org.example.restservice.task.model.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends CrudRepository<TaskEntity, Integer> {

    List<TaskEntity> findAllByBoard(BoardEntity boardEntity);

}
