package pl.marcin.notificationservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.marcin.notificationservice.dto.NotificationDto;

@Service
public class RabbitMqListener {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public ResponseEntity<?> getCourseNotification(){
        Object notification= rabbitTemplate.receiveAndConvert("courseApp");
        if (notification instanceof NotificationDto){
            System.out.println(notification);
            return ResponseEntity.ok((NotificationDto) notification);
        }
        return ResponseEntity.noContent().build();
    }
    @RabbitListener(queues = "courseApp")
    public void listenerNotificationJson(NotificationDto notification) {
        notification.getEmails().forEach(System.out::println);
    }
}
