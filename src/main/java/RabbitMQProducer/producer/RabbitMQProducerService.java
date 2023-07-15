package RabbitMQProducer.producer;

public interface RabbitMQProducerService {

    void sendMessage(String message, String routingKey);
}