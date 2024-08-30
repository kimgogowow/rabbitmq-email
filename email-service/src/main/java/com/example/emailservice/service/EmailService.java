package com.example.emailservice.service;

import com.example.emailservice.dto.EmailRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String email, String body){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kimxxxx@gmail.com");//same as ur application.properties
        message.setTo(email);
        message.setSubject("verification");
        message.setText(body);
        mailSender.send(message);

        System.out.println("Sending email to: " + email + " with verification body: " + body);
    }

    @RabbitListener(queues = "emailQueue")
    public void receiveEmailVerification(Message message) throws JsonProcessingException {
        String json = new String(message.getBody());
        ObjectMapper mapper = new ObjectMapper();
        EmailRequestDTO emailVerification = mapper.readValue(json, EmailRequestDTO.class);
        System.out.println(emailVerification.getTo()+"  "+emailVerification.getToken());
        String body = "The verification code is "+emailVerification.getToken();
        sendEmail(emailVerification.getTo(),body);
        System.out.println("email sent successfully!");
    }
}
