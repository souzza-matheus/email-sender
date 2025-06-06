package com.logico.emailSender.application.usecase;

import com.logico.emailSender.domain.model.EmailRequest;
import com.logico.emailSender.infra.gateway.EmailGateway;
import org.springframework.stereotype.Service;

@Service
public class SendEmailUseCase {

    private final EmailGateway emailGateway;

    public SendEmailUseCase(EmailGateway emailGateway) {
        this.emailGateway = emailGateway;
    }

    public void execute(EmailRequest request) {
        emailGateway.send(request);
    }
}