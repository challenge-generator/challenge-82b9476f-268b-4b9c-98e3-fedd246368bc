package com.fintech.integration.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Modelo de dominio para el servicio de gestión de usuarios.
 * Representa un usuario en el sistema financiero.
 */
public record UserManagement(
    UUID userId,
    String fullName,
    String email,
    String identificationNumber,
    LocalDate birthDate,
    String address,
    List<String> roles,
    String status,
    String correlationId
) {
    /**
     * Constructor compacto para UserManagement.
     * @param userId Identificador único del usuario.
     * @param fullName Nombre completo del usuario.
     * @param email Correo electrónico del usuario.
     * @param identificationNumber Número de identificación.
     * @param birthDate Fecha de nacimiento.
     * @param address Dirección del usuario.
     * @param roles Roles asignados al usuario.
     * @param status Estado del usuario (ACTIVE, INACTIVE, PENDING).
     * @param correlationId Identificador de trazabilidad distribuida.
     */
    public UserManagement {
        Objects.requireNonNull(userId, "userId no puede ser nulo");
        Objects.requireNonNull(fullName, "fullName no puede ser nulo");
        Objects.requireNonNull(email, "email no puede ser nulo");
        Objects.requireNonNull(identificationNumber, "identificationNumber no puede ser nulo");
        Objects.requireNonNull(birthDate, "birthDate no puede ser nulo");
        Objects.requireNonNull(address, "address no puede ser nulo");
        Objects.requireNonNull(roles, "roles no puede ser nulo");
        Objects.requireNonNull(status, "status no puede ser nulo");
        Objects.requireNonNull(correlationId, "correlationId no puede ser nulo");
        
        if (!email.contains("@")) {
            throw new IllegalArgumentException("email debe ser válido");
        }
        
        if (!List.of("ACTIVE", "INACTIVE", "PENDING").contains(status)) {
            throw new IllegalArgumentException("status debe ser ACTIVE, INACTIVE o PENDING");
        }
    }

    /**
     * Valida que el modelo de UserManagement sea consistente.
     * @throws IllegalStateException si alguna validación falla.
     */
    public void validate() {
        if (fullName.trim().isEmpty()) {
            throw new IllegalStateException("fullName no puede estar vacío");
        }
        
        if (roles.isEmpty()) {
            throw new IllegalStateException("roles no puede estar vacío");
        }
    }

    /**
     * Verifica si el usuario tiene un rol específico.
     * @param role Rol a verificar.
     * @return true si el usuario tiene el rol, false en caso contrario.
     */
    public boolean hasRole(String role) {
        return roles.contains(role);
    }
}