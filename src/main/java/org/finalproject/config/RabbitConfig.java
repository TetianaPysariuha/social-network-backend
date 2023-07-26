package org.finalproject.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitUserAndVHost}")
    private String rabbitUserAndVHost;

    @Value("${rabbitPass}")
    private String rabbitPass;

    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("whale.rmq.cloudamqp.com");
        cachingConnectionFactory.setUsername(rabbitUserAndVHost);
        cachingConnectionFactory.setPassword(rabbitPass);
        cachingConnectionFactory.setVirtualHost(rabbitUserAndVHost);
        return cachingConnectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {

        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {

        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue queue1() {

        return new Queue("queueForMessage");
    }

    @Bean
    public Queue queue2() {

        return new Queue("queueForLike");
    }

    @Bean
    public Queue queue3() {

        return new Queue("queueForPost");
    }

    @Bean
    public Queue queue4() {

        return new Queue("queueForComment");
    }

    @Bean
    DirectExchange exchange() {

        return new DirectExchange("ExchangeRabbit", true, false);
    }

    @Bean
    Binding binding1(Queue queue1, DirectExchange exchange) {

        return BindingBuilder.bind(queue1).to(exchange).with("messageRoutingKey1");
    }

    @Bean
    Binding binding2(Queue queue2, DirectExchange exchange) {

        return BindingBuilder.bind(queue2).to(exchange).with("likeRoutingKey");
    }

    @Bean
    Binding binding3(Queue queue3, DirectExchange exchange) {

        return BindingBuilder.bind(queue3).to(exchange).with("postRoutingKey");
    }

    @Bean
    Binding binding4(Queue queue3, DirectExchange exchange) {

        return BindingBuilder.bind(queue3).to(exchange).with("commentRoutingKey");
    }
}
