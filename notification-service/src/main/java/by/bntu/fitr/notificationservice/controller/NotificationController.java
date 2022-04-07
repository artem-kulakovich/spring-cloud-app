package by.bntu.fitr.notificationservice.controller;

import by.bntu.fitr.notificationservice.dto.EmailDTO;
import by.bntu.fitr.notificationservice.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {
    private final SenderService senderService;

    @Autowired
    public NotificationController(SenderService senderService) {
        this.senderService = senderService;
    }


    @KafkaListener(groupId = "1", topics = "notification-topic", containerFactory = "kafkaListenerContainerFactory")
    public void sendNotification(EmailDTO emailDTO) {
        System.out.println(emailDTO.getMsg());
    }
}
