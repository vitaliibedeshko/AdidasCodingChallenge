package com.adidas.email_service.service;

import com.adidas.email_service.payload.SubscriptionPayload;

public interface EmailService {

    void sendEmail(SubscriptionPayload subscriptionPayload);

}
