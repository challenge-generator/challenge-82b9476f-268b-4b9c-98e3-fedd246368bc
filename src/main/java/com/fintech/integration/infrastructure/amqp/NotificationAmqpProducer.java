package com.fintech.integration.infrastructure.amqp;

import com.fintech.integration.domain.Notification;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationAmqpProducer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationAmqpProducer.class);
    private static final String CORRELATION_ID_HEADER = "x-correlation-id";
    private static final String CIRCUIT_BREAKER_NAME = "amqpProducer";

    private final RabbitTemplate rabbitTemplate;
    private final CircuitBreaker circuitBreaker;
    private final String notificationsExchange;
    private final String notificationsRoutingKey;

    public NotificationAmqpProducer(
            RabbitTemplate rabbitTemplate,
            TopicExchange notificationsExchange,
            String notificationsRoutingKey,
            CircuitBreakerRegistry circuitBreakerRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        this.notificationsExchange = notificationsExchange.getName();
        this.notificationsRoutingKey = notificationsRoutingKey;
        
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .build();
        
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        this.circuitBreaker.changeConfig(config);
        
        setupPublisherConfirmations();
    }

    private void setupPublisherConfirmations() {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                logger.debug("Publisher confirmation received for correlationId: {}", 
                    correlationData != null ? correlationData.getId() : "unknown");
            } else {
                logger.error("Publisher confirmation failed for correlationId: {}, cause: {}",
                    correlationData != null ? correlationData.getId() : "unknown",
                    cause);
            }
        });

        rabbitTemplate.setReturnsCallback(returned -> {
            logger.error("Message returned: replyCode={}, replyText={}, exchange={}, routingKey={}",
                returned.getReplyCode(),
                returned.getReplyText(),
                returned.getExchange(),
                returned.getRoutingKey());
        });
    }

    public void sendNotification(Notification notification) {
        notification.validate();
        
        String correlationId = notification.getMetadata(CORRELATION_ID_HEADER);
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        
        try {
            circuitBreaker.executeRunnable(() -> {
                publishNotification(notification, correlationId);
            });
        } catch (Exception e) {
            logger.error("Circuit breaker is open or execution failed for notification: {}", 
                notification, e);
            throw new AmqpPublishingException("Failed to send notification due to circuit breaker", e);
        }
    }

    private void publishNotification(Notification notification, String correlationId) {
        CorrelationData correlationData = new CorrelationData(correlationId);
        
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setPriority(notification.priority().orElse(0));
        messageProperties.setHeader(CORRELATION_ID_HEADER, correlationId);
        
        notification.metadata().forEach((key, value) -> {
            if (!key.equals(CORRELATION_ID_HEADER)) {
                messageProperties.setHeader(key, value);
            }
        });

        Message message = MessageBuilder
                .withBody(notification.toString().getBytes())
                .andProperties(messageProperties)
                .build();

        rabbitTemplate.convertAndSend(
            notificationsExchange,
            notificationsRoutingKey,
            message,
            correlationData
        );
        
        logger.info("Notification sent successfully with correlationId: {}", correlationId);
    }

    public CompletableFuture<Boolean> sendNotificationAsync(Notification notification) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                sendNotification(notification);
                return true;
            } catch (Exception e) {
                logger.error("Failed to send notification asynchronously", e);
                return false;
            }
        });
    }

    public boolean isCircuitBreakerOpen() {
        return circuitBreaker.getState() == CircuitBreaker.State.OPEN;
    }

    public CircuitBreaker.Metrics getCircuitBreakerMetrics() {
        return circuitBreaker.getMetrics();
    }

    public static class AmqpPublishingException extends RuntimeException {
        public AmqpPublishingException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}