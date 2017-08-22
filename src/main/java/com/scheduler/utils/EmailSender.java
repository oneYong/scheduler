package com.scheduler.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * Created by cloud4u on 2016-04-05.
 */
@Component
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;
    private static final Logger log = LoggerFactory.getLogger(EmailSender.class);

    @Async
    public void sendEmail(Email email)throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.setSubject(email.getSubject());
        mimeMessage.setText(email.getText());
        mimeMessage.setSender(new InternetAddress(email.getFromUser()));
        mimeMessage.setRecipient(RecipientType.TO, new InternetAddress(email.getToUser()));

        mailSender.send(mimeMessage);
    }
}
