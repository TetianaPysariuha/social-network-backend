package org.finalproject.service;

public interface RabbitMQProducerService {

    void sendMessage(String message, String routingKey);
}