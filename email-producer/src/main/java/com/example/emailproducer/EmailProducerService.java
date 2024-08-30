package com.example.emailproducer;

import com.example.emailproducer.dto.EmailRequestDTO;
import org.springframework.stereotype.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.security.SecureRandom;

@Service
public class EmailProducerService {

    private final RabbitTemplate rabbitTemplate;

    public EmailProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendVerificationEmail(String email) {
        String verificationCode = generateVerificationCode();
        EmailRequestDTO emailVerification = new EmailRequestDTO(email, "this is subject","this is body",verificationCode);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, emailVerification);  // Use the queue name defined in config
    }

    private String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
