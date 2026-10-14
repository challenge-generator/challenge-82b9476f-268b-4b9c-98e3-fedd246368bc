package com.fintech.integration.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Modelo de dominio para el servicio de evaluación de riesgos.
 * Representa la evaluación de riesgo financiero de un usuario.
 */
public record RiskEvaluation(
    UUID evaluationId,
    UUID userId,
    BigDecimal riskScore,
    String riskLevel,
    LocalDate evaluationDate,
    List<String> riskFactors,
    String correlationId
) {
    /**
     * Constructor compacto paraRiskEvaluation.
     * @param evaluationId Identificador único de la evaluación.
     * @param userId Identificador del usuario.
     * @param riskScore Puntuación de riesgo calculada.
     * @param riskLevel Nivel de riesgo (LOW, MEDIUM, HIGH).
     * @param evaluationDate Fecha de la evaluación.
     * @param riskFactors Factores que influyen en el riesgo.
     * @param correlationId Identificador de trazabilidad distribuida.
     */
    public RiskEvaluation {
        Objects.requireNonNull(evaluationId, "evaluationId no puede ser nulo");
        Objects.requireNonNull(userId, "userId no puede ser nulo");
        Objects.requireNonNull(riskScore, "riskScore no puede ser nulo");
        Objects.requireNonNull(riskLevel, "riskLevel no puede ser nulo");
        Objects.requireNonNull(evaluationDate, "evaluationDate no puede ser nulo");
        Objects.requireNonNull(riskFactors, "riskFactors no puede ser nulo");
        Objects.requireNonNull(correlationId, "correlationId no puede ser nulo");
        
        if (riskScore.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("riskScore debe ser positivo");
        }
        
        if (!List.of("LOW", "MEDIUM", "HIGH").contains(riskLevel)) {
            throw new IllegalArgumentException("riskLevel debe ser LOW, MEDIUM o HIGH");
        }
    }

    /**
     * Valida que el modelo de RiskEvaluation sea consistente.
     * @throws IllegalStateException si alguna validación falla.
     */
    public void validate() {
        if (riskScore.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalStateException("riskScore no puede exceder 100");
        }
        
        if (riskFactors.isEmpty()) {
            throw new IllegalStateException("riskFactors no puede estar vacío");
        }
    }

    /**
     * Convierte el nivel de riesgo a un valor numérico para comparaciones.
     * @return Valor numérico asociado al nivel de riesgo.
     */
    public int riskLevelToNumeric() {
        return switch (riskLevel) {
            case "LOW" -> 1;
            case "MEDIUM" -> 2;
            case "HIGH" -> 3;
            default -> throw new IllegalStateException("Nivel de riesgo desconocido: " + riskLevel);
        };
    }
}