package org.finalproject.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RmqListener {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public RmqListener(SimpMessagingTemplate simpMessagingTemplate) {

        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @RabbitListener(queues = "user-messages")
    public void processMessage(@Payload Message message) {
        // Обработка полученного сообщения и выполнение нужных действий
        // message содержит данные, которые были отправлены через RabbitMQ
        System.out.println("from messages: ");
        System.out.println(new String(message.getBody()));
        String userIdentity = message.getMessageProperties().getReceivedRoutingKey();
        simpMessagingTemplate.convertAndSend("/topic/messages/" + userIdentity, new String(message.getBody()));

    }

    @RabbitListener(queues = "user-notification")
    public void processNotification(@Payload Message message) {
        // Обработка полученного сообщения и выполнение нужных действий
        // message содержит данные, которые были отправлены через RabbitMQ
        System.out.println("from notification: ");
        System.out.println(new String(message.getBody()));

        String userIdentity = message.getMessageProperties().getReceivedRoutingKey();
        simpMessagingTemplate.convertAndSend("/topic/notification/" + userIdentity, new String(message.getBody()));

    }
}