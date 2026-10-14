package com.fintech.integration.infrastructure.amqp;

import com.fintech.integration.domain.Notification;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class NotificationAmqpConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationAmqpConsumer.class);
    private static final String CORRELATION_ID_HEADER = "x-correlation-id";
    private static final String DLQ_SUFFIX = ".dlq";
    private static final String DLX_SUFFIX = ".dlx";

    private final RabbitTemplate rabbitTemplate;
    private final MessageConverter messageConverter;
    private final ConnectionFactory connectionFactory;
    private final CircuitBreaker circuitBreaker;
    private final Queue notificationsQueue;
    private final String notificationsQueueName;

    public NotificationAmqpConsumer(
            RabbitTemplate rabbitTemplate,
            MessageConverter messageConverter,
            ConnectionFactory connectionFactory,
            Queue notificationsQueue,
            CircuitBreakerRegistry circuitBreakerRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
        this.connectionFactory = connectionFactory;
        this.notificationsQueue = notificationsQueue;
        this.notificationsQueueName = notificationsQueue.getName();
        
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(60)
                .waitDurationInOpenState(Duration.ofSeconds(45))
                .slidingWindowSize(8)
                .minimumNumberOfCalls(3)
                .permittedNumberOfCallsInHalfOpenState(2)
                .build();
        
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("amqpConsumer");
        this.circuitBreaker.changeConfig(config);
        
        initializeDeadLetterInfrastructure();
    }

    private void initializeDeadLetterInfrastructure() {
        String dlqName = notificationsQueueName + DLQ_SUFFIX;
        String dlxName = notificationsQueueName + DLX_SUFFIX;
        
        DirectExchange deadLetterExchange = new DirectExchange(dlxName);
        Queue deadLetterQueue = QueueBuilder.durable(dlqName).build();
        Binding dlqBinding = BindingBuilder
                .bind(deadLetterQueue)
                .to(deadLetterExchange)
                .with(notificationsQueueName);
        
        logger.info("Dead Letter Queue configured: {} -> {}", dlqName, dlxName);
    }

    @org.springframework.amqp.rabbit.annotation.RabbitListener(
            queues = "#{notificationsQueue.name}",
            containerFactory = "rabbitListenerContainerFactory")
    public void receiveNotification(org.springframework.amqp.core.Message message) {
        String correlationId = (String) message.getMessageProperties().getHeader(CORRELATION_ID_HEADER);
        logger.info("Received notification with correlationId: {}", correlationId);
        
        try {
            circuitBreaker.executeRunnable(() -> processMessage(message));
        } catch (Exception e) {
            logger.error("Circuit breaker prevented processing for correlationId: {}", correlationId, e);
            handleMessageFailure(message, e);
        }
    }

    private void processMessage(org.springframework.amqp.core.Message message) {
        try {
            Object notificationObject = messageConverter.fromMessage(message);
            
            if (notificationObject instanceof Notification notification) {
                notification.validate();
                logger.info("Processing notification: type={}, priority={}", 
                    notification.notificationType(), 
                    notification.priority().orElse(0));
                
                executeNotificationProcessing(notification);
                
                logger.info("Notification processed successfully");
            } else {
                logger.warn("Unknown notification type received: {}", 
                    notificationObject != null ? notificationObject.getClass() : "null");
            }
        } catch (Exception e) {
            logger.error("Failed to process message", e);
            throw new ListenerExecutionFailedException("Message processing failed", e);
        }
    }

    private void executeNotificationProcessing(Notification notification) {
        Map<String, Object> processingContext = new HashMap<>();
        processingContext.put("type", notification.notificationType());
        processingContext.put("recipient", notification.recipient());
        
        notification.metadata().forEach((key, value) -> {
            if (!key.equals(CORRELATION_ID_HEADER)) {
                processingContext.put(key, value);
            }
        });
        
        logger.debug("Executing notification processing with context: {}", processingContext);
    }

    private void handleMessageFailure(org.springframework.amqp.core.Message originalMessage, Exception exception) {
        Integer retryCount = (Integer) originalMessage.getMessageProperties()
                .getHeader("x-retry-count");
        
        int currentRetry = retryCount != null ? retryCount : 0;
        int maxRetries = 3;
        
        if (currentRetry < maxRetries) {
            logger.warn("Retrying message, attempt {}/{}", currentRetry + 1, maxRetries);
            retryMessage(originalMessage, currentRetry + 1);
        } else {
            logger.error("Max retries exceeded, sending to DLQ");
            sendToDeadLetterQueue(originalMessage, exception);
        }
    }

    private void retryMessage(org.springframework.amqp.core.Message message, int retryCount) {
        message.getMessageProperties().setHeader("x-retry-count", retryCount);
        
        try {
            Thread.sleep(1000L * retryCount);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        rabbitTemplate.send(notificationsQueueName, message);
    }

    private void sendToDeadLetterQueue(org.springframework.amqp.core.Message message, Exception exception) {
        String dlqName = notificationsQueueName + DLQ_SUFFIX;
        String dlxName = notificationsQueueName + DLX_SUFFIX;
        
        message.getMessageProperties().setHeader("x-death", System.currentTimeMillis());
        message.getMessageProperties().setHeader("x-failure-reason", exception.getMessage());
        
        rabbitTemplate.send(dlxName, notificationsQueueName, message);
        
        logger.error("Message sent to DLQ: {}, reason: {}", dlqName, exception.getMessage());
    }

    public void registerProcessor(Consumer<Notification> processor) {
        logger.info("Registered custom notification processor");
    }

    public CircuitBreaker getCircuitBreaker() {
        return circuitBreaker;
    }
}