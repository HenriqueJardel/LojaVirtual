package com.jt.springboot2backend.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstratcEmailService {

    @Autowired(required = true)
    private MailSender mailSender;

    @Autowired
    private JavaMailSender jms;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando envio de email!");
        mailSender.send(msg);
        LOG.info("Email enviado");
    }   

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Simulando envio de email!");
        jms.send(msg);
        LOG.info("Email enviado");
    }
    
}
