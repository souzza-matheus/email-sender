package com.logico.emailSender.application.usecase;

import com.logico.emailSender.application.exeption.RateLimitExceededException;
import com.logico.emailSender.domain.model.EmailRequest;
import com.logico.emailSender.infra.RateLimiter;
import com.logico.emailSender.infra.gateway.EmailGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class SendEmailUseCase {

    private final EmailGateway emailGateway;
    private final RateLimiter rateLimiter;


    public SendEmailUseCase(EmailGateway emailGateway, RateLimiter rateLimiter) {
        this.emailGateway = emailGateway;
        this.rateLimiter = rateLimiter;
    }

    public void execute(EmailRequest request,String clientIp) {
        if (!rateLimiter.isAllowed(clientIp)) {
            throw new RateLimitExceededException();
        }

        emailGateway.send(request);
    }
}