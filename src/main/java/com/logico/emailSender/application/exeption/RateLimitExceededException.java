package com.logico.emailSender.application.exeption;

public class RateLimitExceededException extends StandardErrorMessage {

    public RateLimitExceededException() {
        super("Limite de requisições excedido.", 429);
    }
}