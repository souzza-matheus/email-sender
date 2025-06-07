package com.logico.emailSender.interfaces.controller;

import com.logico.emailSender.application.exeption.RateLimitExceededException;
import com.logico.emailSender.application.exeption.StandardErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(StandardErrorMessage.class)
    public ResponseEntity<String> handleBusinessException(StandardErrorMessage ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }
}