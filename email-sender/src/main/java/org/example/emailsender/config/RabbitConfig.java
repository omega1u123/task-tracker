package org.example.emailsender.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private final String exchangeName = "taskExchange";
    private final String newUserEmailQueueName = "newUserEmailQueue";
    private final String dailyReportQueueName = "dailyReportQueue";


    @Bean(name = "newUserEmailQueue")
    public Queue newUserEmailQueue(){return new Queue(newUserEmailQueueName, false);}

    @Bean(name = "dailyReportQueue")
    public Queue dailyReportQueue(){return new Queue(dailyReportQueueName, false);}

    @Bean
    public Exchange myExchange() {
        return new DirectExchange(exchangeName, false, false);
    }

    @Bean
    public Binding newUserEmailQueueBinding(@Qualifier("newUserEmailQueue") Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("new-user").noargs();
    }

    @Bean
    public Binding dailyReportQueueBinding(@Qualifier("dailyReportQueue") Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("daily-report").noargs();
    }


}
