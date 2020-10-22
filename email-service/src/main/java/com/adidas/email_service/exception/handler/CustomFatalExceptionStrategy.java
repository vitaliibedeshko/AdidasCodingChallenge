package com.adidas.email_service.exception.handler;

import com.adidas.email_service.exception.EmailSenderIsNotImplemented;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;

public class CustomFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {

    public static final String EMAIL_SENDER_IS_NOT_IMPLEMENTED_ERROR_MESSAGE =
            "Email sender is not implemented so email was not send and message with data was send to email DLQ";

    @Override
    public boolean isFatal(Throwable t) {
        Throwable cause = t;
        while (cause != null) {
            if (cause instanceof EmailSenderIsNotImplemented) {
                logger.error(EMAIL_SENDER_IS_NOT_IMPLEMENTED_ERROR_MESSAGE, cause);
                return true;
            } else {
                cause = cause.getCause();
            }
        }
        return super.isFatal(t);
    }

}
