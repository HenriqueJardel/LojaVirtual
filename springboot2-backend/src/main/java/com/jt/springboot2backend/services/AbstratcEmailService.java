package com.jt.springboot2backend.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.jt.springboot2backend.domain.Pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class AbstratcEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine engine;

    @Autowired
    private JavaMailSender jms;

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(obj.getCliente().getEmail());
        smm.setFrom(sender);
        smm.setSubject("Pedido confirmado!");
        smm.setText(obj.toString());
        return smm;
    }

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage smm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(smm);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido obj) {
        try {
        MimeMessage mm = prepareMimeMessageFromPedido(obj);
        sendHtmlEmail(mm);
        } catch(MessagingException e ){
            sendOrderConfirmationEmail(obj);
        }
    }

    protected String htmlFromTemplatePedido(Pedido obj) {
        Context context = new Context();
        context.setVariable("pedido", obj);
        return engine.process("email", context);
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
        MimeMessage mm = jms.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
        mmh.setTo(obj.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Compra confirmada!");
        mmh.setText(htmlFromTemplatePedido(obj), true);
        return mm;
    }
}