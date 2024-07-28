package org.example.restservice.board.repository;

import org.example.restservice.board.model.BoardEntity;
import org.example.restservice.board.model.StatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends CrudRepository<StatusEntity, Integer> {

    StatusEntity findByBoardAndName(BoardEntity board, String statusName);

}
