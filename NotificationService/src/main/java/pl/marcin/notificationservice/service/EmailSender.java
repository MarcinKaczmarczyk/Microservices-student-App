package pl.marcin.notificationservice.service;

import pl.marcin.notificationservice.dto.EmailDto;
import pl.marcin.notificationservice.dto.NotificationDto;

import javax.mail.MessagingException;

public interface EmailSender {
    void sendEmails(NotificationDto notification);
    void sendEmail(EmailDto email) throws MessagingException;
}
