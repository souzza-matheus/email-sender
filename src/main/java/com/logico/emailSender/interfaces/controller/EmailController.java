package com.logico.emailSender.interfaces.controller;

import com.logico.emailSender.application.usecase.SendEmailUseCase;
import com.logico.emailSender.domain.model.EmailRequest;
import com.logico.emailSender.infra.helper.IpExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
public class EmailController {

    private final SendEmailUseCase sendEmailUseCase;


    public EmailController(SendEmailUseCase sendEmailUseCase) {
        this.sendEmailUseCase = sendEmailUseCase;
    }

    @PostMapping("/send")
    public String sendEmail(@Valid @RequestBody EmailRequest emailRequest, HttpServletRequest request) {
        String clientIp = IpExtractor.extractClientIp(request);
        sendEmailUseCase.execute(emailRequest, clientIp);
        return "Email enviado com sucesso!";
    }

}