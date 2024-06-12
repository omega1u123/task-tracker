package org.example.restservice.task.repository;

import org.example.restservice.task.model.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<CommentEntity, Integer> {



}
