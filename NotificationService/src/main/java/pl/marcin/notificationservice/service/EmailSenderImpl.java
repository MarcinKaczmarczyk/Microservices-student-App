package pl.marcin.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.marcin.notificationservice.dto.EmailDto;
import pl.marcin.notificationservice.dto.NotificationDto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;


    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmails(NotificationDto notification) {

        String title = "Pamietaj o kursie: "+ notification.getTitle();
        StringBuilder content= new StringBuilder();
        content.append("Kurs ");
        content.append(notification.getTitle());
        content.append(" rozpoczyna się ");
        content.append(notification.getCourseStartDate().toLocalDate());
        content.append(" o godzinie: ");
        content.append(notification.getCourseStartDate().getHour())
                .append(":")
                .append(notification.getCourseStartDate().getMinute());
        content.append(". Proszę stawić się 15min wcześniej!");
        content.append("\n");
        content.append("Opis kursu: ");
        content.append(notification.getDesc());
        content.append("\n");
        content.append("Kurs kończy się ");
        content.append(notification.getCourseEndDate().getHour()).append(":").append(notification.getCourseEndDate().getMinute());
        content.append("\n");
        content.append("Czekamy na Ciebie!");
        notification.getEmails().forEach(email->{
            try {
                sendEmail(email,title,content.toString());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }


    public void sendEmail(EmailDto email) throws MessagingException {
        sendEmail(email.getTo(),email.getTitle(), email.getContent());
    }

    private void sendEmail(String to, String title, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(content,false);
        javaMailSender.send(mimeMessage);
    }
}
