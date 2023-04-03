package pl.marcin.notificationservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.marcin.notificationservice.dto.EmailDto;

import javax.mail.MessagingException;


@SpringBootTest
class EmailSenderImplTest {

    @Autowired
    EmailSender emailSender;
    @Test
    public void send_email_test() throws MessagingException {
        EmailDto emailDto= new EmailDto();
        emailDto.setTo("mojziomo123@gmail.com");
        emailDto.setTitle("siemaa!!!");
        emailDto.setContent("Majk czek 1 2 1 2");
        emailSender.sendEmail(emailDto);
        System.out.println("poszło");
    }
}