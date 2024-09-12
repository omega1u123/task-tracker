package org.example.restservice.task.model.dto;

import java.sql.Timestamp;

public record TaskDTO(
       int id,
       String title,
       String description,
       String status,
       Timestamp modifiedAt,
       String modifiedBy
){
}
