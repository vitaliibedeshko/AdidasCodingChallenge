package com.adidas.subscription_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String EMAIL_SERVICE_QUEUE_NAME = "email-service-queue";
    public static final String EMAIL_SERVICE_DLQ_NAME = "email-service-DLQ";
    public static final String RABBIT_DEAD_LETTER_EXCHANGE_ARGUMENT_NAME = "x-dead-letter-exchange";
    public static final String RABBIT_DEAD_LETTER_ROUTING_KEY_ARGUMENT_NAME = "x-dead-letter-routing-key";
    public static final String RABBIT_DEFAULT_EXCHANGE_ARGUMENT_VALUE = "";

    @Bean
    public Queue liveQueue() {
        return QueueBuilder.durable(EMAIL_SERVICE_QUEUE_NAME)
                .withArgument(RABBIT_DEAD_LETTER_EXCHANGE_ARGUMENT_NAME, RABBIT_DEFAULT_EXCHANGE_ARGUMENT_VALUE)
                .withArgument(RABBIT_DEAD_LETTER_ROUTING_KEY_ARGUMENT_NAME, EMAIL_SERVICE_DLQ_NAME)
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(EMAIL_SERVICE_DLQ_NAME).build();
    }

    @Bean
    public Jackson2JsonMessageConverter converter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
