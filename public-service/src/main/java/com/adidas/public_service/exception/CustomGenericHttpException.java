package com.adidas.public_service.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomGenericHttpException extends RuntimeException {

    private HttpStatus httpStatus;

    public CustomGenericHttpException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
