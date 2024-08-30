package com.example.emailproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private EmailProducerService emailService;

    @GetMapping("/test")
    public String test(){
        return "hello producer";
    }


    @PostMapping("/send-verification")
   public ResponseEntity<String> sendVerificationEmail(
            @RequestParam String email) {
        emailService.sendVerificationEmail(email);
        return new ResponseEntity<>("Verification email sent.", HttpStatus.OK);
    }
}
