package pl.marcin.notificationservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.marcin.notificationservice.dto.EmailDto;
import pl.marcin.notificationservice.dto.NotificationDto;
import pl.marcin.notificationservice.service.EmailSender;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class MessageController {

//    private final RabbitTemplate rabbitTemplate;

    private final Logger log = LoggerFactory.getLogger(MessageController.class);
    private final EmailSender emailSender;

    public MessageController(/*RabbitTemplate rabbitTemplate,*/ EmailSender emailSender) {
        /* this.rabbitTemplate = rabbitTemplate;*/
        this.emailSender = emailSender;
    }

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody EmailDto email) {

        try {
            emailSender.sendEmail(email);
        } catch (MessagingException e) {
            log.error("Wiadomość do" + email.getTo() + "nie została wysłana. Błąd: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Wiadomość do" + email.getTo() + "nie została wysłana!");
        }
        return ResponseEntity.ok("Wysłano email do: " + email.getTo());
    }

    //    @GetMapping("/notification")
//    public ResponseEntity<?> getCourseNotification(){
//        Object notification= rabbitTemplate.receiveAndConvert("courseApp");
//        if (notification instanceof NotificationDto){
//            System.out.println(notification);
//            return ResponseEntity.ok((NotificationDto) notification);
//        }
//        return ResponseEntity.noContent().build();
//    }
    @RabbitListener(queues = "courseApp")
    public void listenerNotificationJson(NotificationDto notification) {
        System.out.println(notification);
       emailSender.sendEmails(notification);
    }
}
