package com.adidas.email_service.listener;

import com.adidas.email_service.payload.SubscriptionPayload;
import com.adidas.email_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailQueueListener {

    public static final String EMAIL_SERVICE_QUEUE_NAME = "email-service-queue";

    private final EmailService emailService;

    @RabbitListener(queues = EMAIL_SERVICE_QUEUE_NAME)
    public void listenEmailQueue(@Payload SubscriptionPayload subscriptionPayload) {
        emailService.sendEmail(subscriptionPayload);
    }

}
