package com.jt.springboot2backend.services;

import javax.mail.internet.MimeMessage;

import com.jt.springboot2backend.domain.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    
    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);
    
    void sendHtmlEmail(MimeMessage msg);
}