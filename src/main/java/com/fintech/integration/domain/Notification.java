package com.fintech.integration.domain;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Modelo de dominio para el servicio de notificaciones.
 * Representa una notificación enviada a un usuario.
 */
public record Notification(
    UUID notificationId,
    UUID userId,
    String notificationType,
    String subject,
    String message,
    LocalDateTime sentAt,
    String status,
    Map<String, String> metadata,
    String correlationId
) {
    /**
     * Constructor compacto para Notification.
     * @param notificationId Identificador único de la notificación.
     * @param userId Identificador del usuario destino.
     * @param notificationType Tipo de notificación (EMAIL, SMS, PUSH).
     * @param subject Asunto de la notificación.
     * @param message Contenido de la notificación.
     * @param sentAt Fecha y hora de envío.
     * @param status Estado de la notificación (SENT, FAILED, PENDING).
     * @param metadata Metadatos adicionales.
     * @param correlationId Identificador de trazabilidad distribuida.
     */
    public Notification {
        Objects.requireNonNull(notificationId, "notificationId no puede ser nulo");
        Objects.requireNonNull(userId, "userId no puede ser nulo");
        Objects.requireNonNull(notificationType, "notificationType no puede ser nulo");
        Objects.requireNonNull(subject, "subject no puede ser nulo");
        Objects.requireNonNull(message, "message no puede ser nulo");
        Objects.requireNonNull(sentAt, "sentAt no puede ser nulo");
        Objects.requireNonNull(status, "status no puede ser nulo");
        Objects.requireNonNull(metadata, "metadata no puede ser nulo");
        Objects.requireNonNull(correlationId, "correlationId no puede ser nulo");
        
        if (!List.of("EMAIL", "SMS", "PUSH").contains(notificationType)) {
            throw new IllegalArgumentException("notificationType debe ser EMAIL, SMS o PUSH");
        }
        
        if (!List.of("SENT", "FAILED", "PENDING").contains(status)) {
            throw new IllegalArgumentException("status debe ser SENT, FAILED o PENDING");
        }
    }

    /**
     * Valida que el modelo de Notification sea consistente.
     * @throws IllegalStateException si alguna validación falla.
     */
    public void validate() {
        if (subject.trim().isEmpty()) {
            throw new IllegalStateException("subject no puede estar vacío");
        }
        
        if (message.trim().isEmpty()) {
            throw new IllegalStateException("message no puede estar vacío");
        }
    }

    /**
     * Obtiene un metadato por clave.
     * @param key Clave del metadato.
     * @return Valor del metadato o null si no existe.
     */
    public String getMetadata(String key) {
        return metadata.get(key);
    }

    /**
     * Añade un metadato.
     * @param key Clave del metadato.
     * @param value Valor del metadato.
     * @return Nueva instancia con el metadato añadido.
     */
    public Notification withMetadata(String key, String value) {
        Map<String, String> newMetadata = new java.util.HashMap<>(metadata);
        newMetadata.put(key, value);
        return new Notification(notificationId, userId, notificationType, subject, message, sentAt, status, newMetadata, correlationId);
    }
}