//package org.finalproject.service;
//
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@EnableRabbit
//public class RabbitMQConsumer {
//
//
//    @RabbitListener(queues = "queueForMessage")
//    public void processQueueForMessage(String message) {
//
//        System.out.printf("Received from queueForMessage : %s ", new String(message.getBytes()));
//    }
//
//    @RabbitListener(queues = "queueForLike")
//    public void processQueueForLike(String message) {
//
//        System.out.printf("Received from queueForLike : %s ", new String(message.getBytes()));
//    }
//
//    @RabbitListener(queues = "queueForPost")
//    public void processQueueForPost(String message) {
//
//        System.out.printf("Received from queueForPost : %s ", new String(message.getBytes()));
//    }
//
//    @RabbitListener(queues = "queueForComment")
//    public void processQueueForComment(String message) {
//
//        System.out.printf("Received from queueForComment : %s ", new String(message.getBytes()));
//    }
//}