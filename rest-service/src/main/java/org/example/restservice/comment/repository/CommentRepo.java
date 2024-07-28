package org.example.restservice.comment.repository;

import org.example.restservice.comment.model.CommentEntity;
import org.example.restservice.task.model.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByTask(TaskEntity task);
}
