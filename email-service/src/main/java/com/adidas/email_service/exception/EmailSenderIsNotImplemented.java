package com.adidas.email_service.exception;

public class EmailSenderIsNotImplemented extends RuntimeException{

    public EmailSenderIsNotImplemented(String message) {
        super(message);
    }

}
