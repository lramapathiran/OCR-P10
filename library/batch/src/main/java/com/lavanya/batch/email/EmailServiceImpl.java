package com.lavanya.batch.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("EmailService")
public class EmailServiceImpl implements EmailService {

    private static final String NOREPLY_ADDRESS = "noreply@baeldung.com";

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SimpleMailMessage template;
    
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
    
//    @Override
    public void sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               String templateModel) {
        String text = String.format(template.getText(), templateModel);  
        sendSimpleMessage(to, subject, text);
    }

    
}