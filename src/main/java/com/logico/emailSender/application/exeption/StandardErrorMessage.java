package com.logico.emailSender.application.exeption;

import lombok.Getter;

;


@Getter
public class StandardErrorMessage extends RuntimeException {

    private final int statusCode;

    public StandardErrorMessage(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}