package by.bntu.fitr.notificationservice.service;

import by.bntu.fitr.notificationservice.dto.EmailDTO;

public interface SenderService {

    void send(EmailDTO emailDTO);
}
