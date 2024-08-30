package com.example.emailservice;

import com.example.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EmailServiceApplication {
    @Autowired
    private EmailService emailService;


    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }
    //test code
    /*
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(){
        emailService.sendVerificationEmail("liangxiao325@outlook.com");
    }
     */

}
