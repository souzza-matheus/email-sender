package com.logico.emailSender.interfaces.controller;

import com.logico.emailSender.application.usecase.SendEmailUseCase;
import com.logico.emailSender.domain.model.EmailRequest;
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
    public String sendEmail(@Valid @RequestBody EmailRequest emailRequest) {
        sendEmailUseCase.execute(emailRequest);
        return "Email enviado com sucesso!";
    }

}