package com.mikolaj.nalecz.biblioteka.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class EmailComponent {

    private JavaMailSender emailSender;
    private TemplateEngine templateEngine;

    @SneakyThrows
    @Async
    public void wyslijPonaglenie(String email, BigDecimal kwota) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        var context = new Context();
        context.setVariable("kwota", kwota.setScale(2, RoundingMode.DOWN).toPlainString());

        helper.setTo(email);
        helper.setSubject("Niezwrócone książki");
        helper.setText(templateEngine.process("email/przypomnienie", context), true);

        emailSender.send(message);
    }

}
