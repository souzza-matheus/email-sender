package com.logico.emailSender.application.exeption;


public class InvalidTokenException extends StandardErrorMessage{

    public InvalidTokenException() {
        super("Token inválido ou expirado.", 401);
    }
}