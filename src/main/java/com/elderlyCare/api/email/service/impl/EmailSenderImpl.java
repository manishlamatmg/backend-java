package com.elderlyCare.api.email.service.impl;

import com.elderlyCare.api.email.dto.EmailDTO;
import com.elderlyCare.api.email.entity.EmailTemplate;
import com.elderlyCare.api.email.repository.EmailTemplateRepository;
import com.elderlyCare.api.email.service.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class EmailSenderImpl implements EmailSender {
    private final JavaMailSender javaMailSender;
    private final ITemplateEngine templateEngine;
    private final EmailTemplateRepository emailTemplateRepository;

    public EmailSenderImpl(JavaMailSender javaMailSender, ITemplateEngine templateEngine, EmailTemplateRepository emailTemplateRepository) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.emailTemplateRepository = emailTemplateRepository;
    }


    public void sendEmail(EmailDTO emailDTO) {
        Context context = new Context();
        context.setVariables(emailDTO.getProperties());
        try {
            EmailTemplate emailTemplate = emailTemplateRepository.getEmailTemplateByEmailTemplateType_Name(emailDTO.getTemplateName());

            String process = templateEngine.process(emailTemplate.getTemplate(), context);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom(emailTemplate.getFromAddress());
            mimeMessageHelper.setTo(emailDTO.getToAddress());
            mimeMessageHelper.setSubject(emailTemplate.getSubject());
            mimeMessageHelper.setText(process, true);
            javaMailSender.send(mimeMessage);
        }  catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}

