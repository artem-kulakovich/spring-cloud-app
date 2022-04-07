package by.bntu.fitr.notificationservice.service.impl;

import by.bntu.fitr.notificationservice.dto.EmailDTO;
import by.bntu.fitr.notificationservice.exception.CantSendEmailException;
import by.bntu.fitr.notificationservice.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class EmailSenderServiceImpl implements SenderService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(EmailDTO emailDTO) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(emailDTO.getTo());
            simpleMailMessage.setText(emailDTO.getMsg());
            simpleMailMessage.setSubject(emailDTO.getSubject());
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            throw new CantSendEmailException(e.getMessage());
        }
    }
}
