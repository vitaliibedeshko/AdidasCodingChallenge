package com.adidas.subscription_service.exception.handler;

import com.adidas.subscription_service.exception.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GenericGlobalExceptionHandler {

    public static final String AMQP_EXCEPTION_MESSAGE = "Amqp throw exception: ";

    @ExceptionHandler(AmqpException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorMessage handleAccessDeniedException(Exception ex) {
        log.error(AMQP_EXCEPTION_MESSAGE, ex);
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handlePSQLException(Throwable throwable) {
        log.error("DataIntegrityViolationException: ", throwable);
        StringBuilder message = new StringBuilder();
        while (throwable != null) {
            message.append(throwable.getMessage());
            throwable = throwable.getCause();
        }
        return new ErrorMessage(message.toString());
    }


}
