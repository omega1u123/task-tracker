package org.example.emailsender.service;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RmqMessageModel {

    private String email;

    private int completedTasksCount;

}
