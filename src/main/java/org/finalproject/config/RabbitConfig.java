package org.finalproject.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitUserAndVHost:a}")
    private String rabbitUserAndVHost;
    @Value("${rabbitPass:a}")
    private String rabbitPass;
    @Value("${cloudamqp_url:localhost}")
    private String cloudamqpUrl;

    @Bean
    public CachingConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri("amqps://tnjaskux:CwZKUs5tzAcTPPlLIhT9vLxTWH7km98o@whale.rmq.cloudamqp.com/tnjaskux");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public Queue messageQueue() {

        return new Queue("user-messages");
    }

    @Bean
    public Queue notificationQueue() {

        return new Queue("user-notification");
    }

    @Bean
    public MessageConverter converter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange messagesExchange() {

        return new TopicExchange("messages-exchange");
    }

    @Bean
    public TopicExchange notificationExchange() {

        return new TopicExchange("notification-exchange");
    }


    @Bean
    public Binding messageBinding(Queue messageQueue, TopicExchange messagesExchange) {

        return BindingBuilder.bind(messageQueue).to(messagesExchange).with("user.#");
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, TopicExchange notificationExchange) {

        return BindingBuilder.bind(notificationQueue).to(notificationExchange).with("user.#");
    }
}
