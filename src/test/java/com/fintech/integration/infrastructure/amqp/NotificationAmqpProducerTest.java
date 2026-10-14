package com.fintech.integration.infrastructure.amqp;

import com.fintech.integration.domain.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para NotificationAmqpProducer")
class NotificationAmqpProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private MessageConverter messageConverter;

    private NotificationAmqpProducer producer;

    private static final String EXCHANGE = "notifications.exchange";
    private static final String ROUTING_KEY = "notifications.routing.key";

    @BeforeEach
    void setUp() {
        producer = new NotificationAmqpProducer(rabbitTemplate, EXCHANGE, ROUTING_KEY);
        when(rabbitTemplate.getMessageConverter()).thenReturn(messageConverter);
    }

    @Test
    @DisplayName("Debe enviar notificación exitosamente")
    void sendNotification_validNotification_sendsSuccessfully() {
        Notification notification = new Notification(
            UUID.randomUUID().toString(),
            "USER_001",
            "EMAIL",
            "Test message",
            Map.of("correlationId", "corr-123", "priority", "HIGH")
        );

        doNothing().when(rabbitTemplate).convertAndSend(
            eq(EXCHANGE),
            eq(ROUTING_KEY),
            any(Notification.class)
        );

        producer.sendNotification(notification);

        verify(rabbitTemplate, times(1)).convertAndSend(
            eq(EXCHANGE),
            eq(ROUTING_KEY),
            eq(notification)
        );
    }

    @Test
    @DisplayName("Debe enviar notificación con metadatos personalizados")
    void sendNotification_withCustomMetadata_sendsWithMetadata() {
        Notification notification = new Notification(
            UUID.randomUUID().toString(),
            "USER_002",
            "SMS",
            "Alert message",
            Map.of("correlationId", "corr-456", "retryCount", "0", "source", "grpc-service")
        );

        doNothing().when(rabbitTemplate).convertAndSend(anyString(), anyString(), any(Notification.class));

        producer.sendNotification(notification);

        verify(rabbitTemplate, times(1)).convertAndSend(
            eq(EXCHANGE),
            eq(ROUTING_KEY),
            eq(notification)
        );

        assertThat(notification.getMetadata("correlationId")).isEqualTo("corr-456");
    }

    @Test
    @DisplayName("Debe manejar error cuando la notificación es nula")
    void sendNotification_nullNotification_throwsException() {
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> producer.sendNotification(null)
        );
    }

    @Test
    @DisplayName("Debe validar notificación antes de enviar")
    void sendNotification_invalidNotificationId_throwsException() {
        Notification invalidNotification = new Notification(
            null,
            "USER_001",
            "EMAIL",
            "Test",
            Map.of()
        );

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> producer.sendNotification(invalidNotification)
        );
    }

    @Test
    @DisplayName("Debe validar usuario receptor antes de enviar")
    void sendNotification_invalidReceiver_throwsException() {
        Notification invalidNotification = new Notification(
            UUID.randomUUID().toString(),
            null,
            "EMAIL",
            "Test",
            Map.of()
        );

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> producer.sendNotification(invalidNotification)
        );
    }

    @Test
    @DisplayName("Debe validar tipo de notificación antes de enviar")
    void sendNotification_invalidType_throwsException() {
        Notification invalidNotification = new Notification(
            UUID.randomUUID().toString(),
            "USER_001",
            null,
            "Test",
            Map.of()
        );

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> producer.sendNotification(invalidNotification)
        );
    }

    @Test
    @DisplayName("Debe permitir agregar metadatos a la notificación")
    void notification_withMetadata_addedCorrectly() {
        Notification original = new Notification(
            UUID.randomUUID().toString(),
            "USER_001",
            "EMAIL",
            "Test message",
            Map.of("key1", "value1")
        );

        Notification withMeta = original.withMetadata("key2", "value2");

        assertThat(withMeta.getMetadata("key1")).isEqualTo("value1");
        assertThat(withMeta.getMetadata("key2")).isEqualTo("value2");
    }

    @Test
    @DisplayName("Debe manejar error de RabbitMQ al enviar")
    void sendNotification_rabbitFailure_throwsException() {
        Notification notification = new Notification(
            UUID.randomUUID().toString(),
            "USER_001",
            "EMAIL",
            "Test message",
            Map.of()
        );

        doThrow(new org.springframework.amqp.AmqpException("Connection refused"))
            .when(rabbitTemplate).convertAndSend(anyString(), anyString(), any(Notification.class));

        org.junit.jupiter.api.Assertions.assertThrows(
            org.springframework.amqp.AmqpException.class,
            () -> producer.sendNotification(notification)
        );
    }
}