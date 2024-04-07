package org.example.emailsender.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NewUserEmailSender {

    @RabbitListener(queues = "emailQueue")
    public void newUserEmail(String message){
        System.out.println(message);
    }

}
