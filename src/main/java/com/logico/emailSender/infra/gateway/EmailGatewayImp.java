package com.logico.emailSender.infra.gateway;


import com.logico.emailSender.domain.model.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailGatewayImp implements EmailGateway{

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${app.email.to}")
    private String destinationEmail;
    @Override
    public void send(EmailRequest request) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(destinationEmail);
        message.setReplyTo(request.getEmail());

        message.setSubject("Novo pedido de orçamento de " + request.getNome());
        message.setText(String.format(
                "Nome: %s\nEmail: %s\nEmpresa: %s\nTelefone: %s\nServiço de Interesse: %s\nMensagem: %s",
                request.getNome(), request.getEmail(), request.getEmpresa(), request.getTelefone(),
                request.getServico(), request.getMensagem()
        ));

        mailSender.send(message);
    }
}
