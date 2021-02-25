package com.pocms.email.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    public static final String QUEUE_EMAIL_VENDA = "queue.email.venda.persist";

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(QUEUE_EMAIL_VENDA)
                .build();
    }
}
