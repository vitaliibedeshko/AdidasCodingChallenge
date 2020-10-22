package com.adidas.public_service.exception.handler;

import com.adidas.public_service.exception.CustomGenericHttpException;
import com.adidas.public_service.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGenericExceptionHandler {

    @ExceptionHandler(CustomGenericHttpException.class)
    public ResponseEntity<ErrorResponse> customGenericExceptionHandler(CustomGenericHttpException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), e.getHttpStatus());
    }

}
