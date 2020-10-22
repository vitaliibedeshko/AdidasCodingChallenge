package com.adidas.email_service.service;

import com.adidas.email_service.exception.EmailSenderIsNotImplemented;
import com.adidas.email_service.payload.SubscriptionPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    public static final String EMAIL_WAS_NOT_SEND_ERROR_MESSAGE = "Email was not send with payload: ";

    public void sendEmail(SubscriptionPayload subscriptionPayload) {
        throw new EmailSenderIsNotImplemented(EMAIL_WAS_NOT_SEND_ERROR_MESSAGE + subscriptionPayload);
    }

}
