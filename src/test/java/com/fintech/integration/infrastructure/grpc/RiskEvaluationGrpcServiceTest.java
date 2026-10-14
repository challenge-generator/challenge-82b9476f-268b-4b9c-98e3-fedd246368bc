package com.fintech.integration.infrastructure.grpc;

import com.fintech.integration.domain.RiskEvaluation;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para RiskEvaluationGrpcService")
class RiskEvaluationGrpcServiceTest {

    @Mock
    private RiskEvaluationGrpcService grpcService;

    @Mock
    private ManagedChannel managedChannel;

    private GrpcCleanupRule grpcCleanup;

    @BeforeEach
    void setUp() {
        grpcCleanup = new GrpcCleanupRule();
    }

    @Test
    @DisplayName("Debe evaluar riesgo exitosamente con nivel bajo")
    void evaluateRisk_withLowRiskLevel_returnsSuccess() {
        RiskEvaluation evaluation = new RiskEvaluation("CLIENT_001", "LOW", "2024-01-15T10:30:00Z");
        evaluation.validate();

        assertThat(evaluation.riskLevelToNumeric()).isEqualTo(1);
        assertThat(evaluation.riskLevelToNumeric()).isLessThanOrEqualTo(3);
    }

    @Test
    @DisplayName("Debe evaluar riesgo exitosamente con nivel medio")
    void evaluateRisk_withMediumRiskLevel_returnsSuccess() {
        RiskEvaluation evaluation = new RiskEvaluation("CLIENT_002", "MEDIUM", "2024-01-15T11:00:00Z");
        evaluation.validate();

        assertThat(evaluation.riskLevelToNumeric()).isEqualTo(2);
        assertThat(evaluation.riskLevelToNumeric()).isGreaterThan(1);
        assertThat(evaluation.riskLevelToNumeric()).isLessThanOrEqualTo(5);
    }

    @Test
    @DisplayName("Debe evaluar riesgo exitosamente con nivel alto")
    void evaluateRisk_withHighRiskLevel_returnsSuccess() {
        RiskEvaluation evaluation = new RiskEvaluation("CLIENT_003", "HIGH", "2024-01-15T12:00:00Z");
        evaluation.validate();

        assertThat(evaluation.riskLevelToNumeric()).isEqualTo(5);
        assertThat(evaluation.riskLevelToNumeric()).isGreaterThan(3);
    }

    @Test
    @DisplayName("Debe manejar error cuando el cliente es inválido")
    void evaluateRisk_withInvalidClientId_throwsException() {
        RiskEvaluation evaluation = new RiskEvaluation(null, "LOW", "2024-01-15T10:30:00Z");

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            evaluation::validate
        );
    }

    @Test
    @DisplayName("Debe manejar error cuando el nivel de riesgo es inválido")
    void evaluateRisk_withInvalidRiskLevel_throwsException() {
        RiskEvaluation evaluation = new RiskEvaluation("CLIENT_001", "INVALID", "2024-01-15T10:30:00Z");

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            evaluation::validate
        );
    }

    @Test
    @DisplayName("Debe manejar error cuando la fecha es inválida")
    void evaluateRisk_withInvalidDate_throwsException() {
        RiskEvaluation evaluation = new RiskEvaluation("CLIENT_001", "LOW", "invalid-date");

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            evaluation::validate
        );
    }

    @Test
    @DisplayName("Debe convertir niveles de riesgo a valores numéricos correctamente")
    void riskLevelToNumeric_allLevels_returnsCorrectValues() {
        RiskEvaluation lowRisk = new RiskEvaluation("C1", "LOW", "2024-01-01T00:00:00Z");
        RiskEvaluation mediumRisk = new RiskEvaluation("C2", "MEDIUM", "2024-01-01T00:00:00Z");
        RiskEvaluation highRisk = new RiskEvaluation("C3", "HIGH", "2024-01-01T00:00:00Z");

        assertThat(lowRisk.riskLevelToNumeric()).isLessThan(mediumRisk.riskLevelToNumeric());
        assertThat(mediumRisk.riskLevelToNumeric()).isLessThan(highRisk.riskLevelToNumeric());
    }

    @Test
    @DisplayName("Debe verificar que el canal gRPC se construye correctamente")
    void channelCreation_withValidConfig_buildsSuccessfully() throws Exception {
        String serverName = InProcessServerBuilder.generateName();
        InProcessServerBuilder serverBuilder = InProcessServerBuilder.forName(serverName).directExecutor();
        grpcCleanup.register(serverBuilder.build().start());

        ManagedChannel channel = InProcessChannelBuilder
            .forName(serverName)
            .directExecutor()
            .build();
        grpcCleanup.register(channel);

        assertThat(channel).isNotNull();
        assertThat(channel.isShutdown()).isFalse();
    }
}