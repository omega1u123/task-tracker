package org.example.emailsender.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewUserEmailSender {

    private final JavaMailSender javaMailSender;
    private final ObjectMapper objectMapper;

    @Value("${spring.mail.username}")
    private String fromMail;

    @RabbitListener(queues = "newUserEmailQueue")
    public void sendNewUserEmail(String message){
        log.info("email from rMQ: {}", message);
        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        simpleMessage.setFrom(fromMail);
        simpleMessage.setSubject("registration");
        simpleMessage.setText("welcome to Task tracker");
        simpleMessage.setTo(message);
        javaMailSender.send(simpleMessage);
        log.info("email was send to : {}", message);
    }

    @RabbitListener(queues = "dailyReportQueue")
    public void sendDailyReport(String message) throws JsonProcessingException {
        RmqMessageModel receivedMessage = objectMapper.readValue(message, RmqMessageModel.class);
        log.info("message from rMQ: {} {}", receivedMessage.getEmail(), receivedMessage.getCompletedTasksCount());

        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        simpleMessage.setFrom(fromMail);
        simpleMessage.setSubject("daily report");
        simpleMessage.setText(String.format("hello, today you completed %d tasks", receivedMessage.getCompletedTasksCount() ));
        simpleMessage.setTo(message);
        javaMailSender.send(simpleMessage);
    }


}
