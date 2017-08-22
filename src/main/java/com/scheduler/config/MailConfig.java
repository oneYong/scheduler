package com.scheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by cloud4u on 2016-04-05.
 */
@Configuration
@EnableConfigurationProperties({ConfigProperties.class})
public class MailConfig {
    @Autowired
    private ConfigProperties configProperties;

    @Bean
    public JavaMailSender mailSender(){
        String mailHost = configProperties.getMail().get("host").toString();
        int mailPort =(Integer)configProperties.getMail().get("port");
        String mailUsername = configProperties.getMail().get("username").toString();
        String mailPassword = configProperties.getMail().get("password").toString();
        String mailEncoding = configProperties.getMail().get("encoding").toString();

        String smtpAuth = configProperties.getMail().get("smtpAuth").toString();
        String smtpStarttlsEnable = configProperties.getMail().get("smtpStarttlsEnable").toString();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);
        mailSender.setDefaultEncoding(mailEncoding);

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.starttls.enable", smtpStarttlsEnable);

        mailSender.setJavaMailProperties(properties);

        return mailSender;

    }
}
