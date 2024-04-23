package org.example.sheduler.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RmqMessageModel {

    private String email;

    private int completedTasksCount;

}
