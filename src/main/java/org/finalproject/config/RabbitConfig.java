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

    // @Bean
    // public ConnectionFactory connectionFactory() {

    //     CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(cloudamqpUrl);
    //     // cachingConnectionFactory.setUsername(rabbitUserAndVHost);
    //     // cachingConnectionFactory.setPassword(rabbitPass);
    //     // cachingConnectionFactory.setVirtualHost(rabbitUserAndVHost);
    //     return cachingConnectionFactory;
    // }
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

        return new Queue("user-messages"); // Создание очереди
    }

    @Bean
    public Queue notificationQueue() {

        return new Queue("user-notification"); // Создание очереди
    }

    @Bean
    public Queue postQueue() {

        return new Queue("topic-posts"); // Создание очереди
    }

    @Bean
    public MessageConverter converter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange messagesExchange() {

        return new TopicExchange("messages-exchange"); // Создание обменника
    }

    @Bean
    public TopicExchange notificationExchange() {

        return new TopicExchange("notification-exchange"); // Создание обменника
    }

    @Bean
    public TopicExchange postExchange() {

        return new TopicExchange("post-exchange"); // Создание обменника
    }

    @Bean
    public Binding messageBinding(Queue messageQueue, TopicExchange messagesExchange) {

        return BindingBuilder.bind(messageQueue).to(messagesExchange).with("user.#");
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, TopicExchange notificationExchange) {

        return BindingBuilder.bind(notificationQueue).to(notificationExchange).with("user.#");
    }

    @Bean
    public Binding postBinding(Queue postQueue, TopicExchange postExchange) {

        return BindingBuilder.bind(postQueue).to(postExchange).with("post");
    }

//    @Bean
//    public AmqpAdmin amqpAdmin() {
//
//        return new RabbitAdmin(connectionFactory());
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//
//        return new RabbitTemplate(connectionFactory());
//    }
//
//    @Bean
//    public Queue queue1() {
//
//        return new Queue("queueForMessage");
//    }
//
//    @Bean
//    public Queue queue2() {
//
//        return new Queue("queueForLike");
//    }
//
//    @Bean
//    public Queue queue3() {
//
//        return new Queue("queueForPost");
//    }
//
//    @Bean
//    public Queue queue4() {
//
//        return new Queue("queueForComment");
//    }
//
//    @Bean
//    DirectExchange exchange() {
//
//        return new DirectExchange("ExchangeRabbit", true, false);
//    }
//
//    @Bean
//    Binding binding1(Queue queue1, DirectExchange exchange) {
//
//        return BindingBuilder.bind(queue1).to(exchange).with("messageRoutingKey");
//    }
//
//    @Bean
//    Binding binding2(Queue queue2, DirectExchange exchange) {
//
//        return BindingBuilder.bind(queue2).to(exchange).with("likeRoutingKey");
//    }
//
//    @Bean
//    Binding binding3(Queue queue3, DirectExchange exchange) {
//
//        return BindingBuilder.bind(queue3).to(exchange).with("postRoutingKey");
//    }
//
//    @Bean
//    Binding binding4(Queue queue4, DirectExchange exchange) {
//
//        return BindingBuilder.bind(queue4).to(exchange).with("commentRoutingKey");
//    }
}
