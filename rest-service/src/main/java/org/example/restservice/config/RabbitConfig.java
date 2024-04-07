package org.example.restservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private final String exchangeName = "taskExchange";
    private final String queueName = "emailQueue";

    @Bean
    public Queue myQueue(){return new Queue(queueName, false);}

    @Bean
    public Exchange myExchange(){return new FanoutExchange(exchangeName, false, false);}

    @Bean
    public Binding myBinding(Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("all.key").noargs();
    }

}
