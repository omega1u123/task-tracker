package org.example.sheduler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sheduler.domain.RmqMessageModel;
import org.example.sheduler.domain.TaskEntity;
import org.example.sheduler.domain.UserEntity;
import org.example.sheduler.repository.TaskRepo;
import org.example.sheduler.repository.UserRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(cron = "0 00 00 * * *")
    private void sendDailyReport() throws JsonProcessingException {
        List<UserEntity> users = new ArrayList<>();

        userRepo.findAll().forEach(users::add);

        for(UserEntity user : users){
            RmqMessageModel message = new RmqMessageModel();
            message.setEmail(user.getEmail());
            List<TaskEntity> tasks = new ArrayList<>(taskRepo.findAllByUserEntityAndStatus(user, "completed"));
            message.setCompletedTasksCount(tasks.size());
            rabbitTemplate.convertAndSend("taskExchange", "daily-report", objectMapper.writeValueAsString(message));
            log.info("daily report was send to rMQ");
        }

    }

}
