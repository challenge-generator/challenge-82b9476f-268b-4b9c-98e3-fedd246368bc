package com.fintech.integration.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AmqpConfig {

    private static final Logger log = LoggerFactory.getLogger(AmqpConfig.class);

    @Value("${spring.rabbitmq.host:localhost}")
    private String rabbitHost;

    @Value("${spring.rabbitmq.port:5672}")
    private int rabbitPort;

    @Value("${spring.rabbitmq.username:guest}")
    private String rabbitUsername;

    @Value("${spring.rabbitmq.password:guest}")
    private String rabbitPassword;

    @Value("${amqp.notifications.queue:notifications-queue}")
    private String notificationsQueueName;

    @Value("${amqp.notifications.exchange:notifications-exchange}")
    private String notificationsExchangeName;

    @Value("${amqp.notifications.routing-key:notifications.routing.key}")
    private String notificationsRoutingKey;

    @Value("${amqp.dlq.enabled:true}")
    private boolean dlqEnabled;

    @Value("${amqp.retry.max-attempts:3}")
    private int maxRetryAttempts;

    @Value("${amqp.retry.initial-interval:1000}")
    private long initialRetryInterval;

    @Value("${amqp.retry.multiplier:2.0}")
    private double retryMultiplier;

    @Value("${amqp.retry.max-interval:10000}")
    private long maxRetryInterval;

    @Bean
    public Queue notificationsQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000);
        
        if (dlqEnabled) {
            String dlqName = notificationsQueueName + ".dlq";
            args.put("x-dead-letter-exchange", "");
            args.put("x-dead-letter-routing-key", dlqName);
            log.info("DLQ configurada para la cola de notificaciones: {}", dlqName);
        }
        
        return QueueBuilder.durable(notificationsQueueName).withArguments(args).build();
    }

    @Bean
    public Queue deadLetterQueue() {
        if (!dlqEnabled) {
            return null;
        }
        String dlqName = notificationsQueueName + ".dlq";
        log.info("Creando cola de mensajes muertos: {}", dlqName);
        return QueueBuilder.durable(dlqName).build();
    }

    @Bean
    public TopicExchange notificationsExchange() {
        log.info("Configurando exchange de notificaciones: {}", notificationsExchangeName);
        return new TopicExchange(notificationsExchangeName);
    }

    @Bean
    public Binding notificationsBinding(Queue notificationsQueue, TopicExchange notificationsExchange) {
        return BindingBuilder.bind(notificationsQueue)
                .to(notificationsExchange)
                .with(notificationsRoutingKey);
    }

    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue) {
        if (!dlqEnabled || deadLetterQueue == null) {
            return null;
        }
        String dlqName = notificationsQueueName + ".dlq";
        return BindingBuilder.bind(deadLetterQueue)
                .to(new TopicExchange(""))
                .with(dlqName);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        template.setRetryTemplate(retryTemplate());
        log.info("RabbitTemplate configurado con reintentos");
        return template;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(initialRetryInterval);
        backOffPolicy.setMultiplier(retryMultiplier);
        backOffPolicy.setMaxInterval(maxRetryInterval);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(maxRetryAttempts);
        retryTemplate.setRetryPolicy(retryPolicy);

        log.info("RetryTemplate configurado: maxAttempts={}, initialInterval={}, multiplier={}",
                maxRetryAttempts, initialRetryInterval, retryMultiplier);

        return retryTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {
        
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setDefaultRequeueRejected(false);
        factory.setPrefetchCount(10);
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        
        log.info("RabbitListenerContainerFactory configurado con prefetch=10, concurrentConsumers=3");
        return factory;
    }

    @Bean
    public org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory 
            directRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        
        org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory factory =
            new org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setDefaultRequeueRejected(false);
        factory.setPrefetchCount(1);
        
        log.info("DirectRabbitListenerContainerFactory configurado para procesamiento exclusivo");
        return factory;
    }

    @Bean
    public org.springframework.amqp.rabbit.connection.CachingConnectionFactory 
            cachingConnectionFactory(ConnectionFactory connectionFactory) {
        
        org.springframework.amqp.rabbit.connection.CachingConnectionFactory cachingFactory =
            new org.springframework.amqp.rabbit.connection.CachingConnectionFactory(connectionFactory);
        cachingFactory.setCacheMode(org.springframework.amqp.rabbit.connection.CachingConnectionFactory.CacheMode.CHANNEL);
        cachingFactory.setChannelCacheSize(25);
        cachingFactory.setConnectionTimeout(5000);
        
        log.info("CachingConnectionFactory configurado con cacheSize=25");
        return cachingFactory;
    }
}