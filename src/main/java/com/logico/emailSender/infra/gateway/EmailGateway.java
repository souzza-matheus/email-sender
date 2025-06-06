package com.logico.emailSender.infra.gateway;

import com.logico.emailSender.domain.model.EmailRequest;

public interface EmailGateway {
    void send(EmailRequest request);
}
