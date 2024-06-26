package org.example.restservice.board.repository;

import org.example.restservice.board.model.BoardEntity;
import org.example.restservice.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepo extends JpaRepository<BoardEntity, Integer> {

    Iterable<BoardEntity> findBoardEntitiesByUsers(UserEntity user);

}
