package com.jt.springboot2backend.config;

import com.jt.springboot2backend.services.EmailService;
import com.jt.springboot2backend.services.SmtpEmailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    
    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
