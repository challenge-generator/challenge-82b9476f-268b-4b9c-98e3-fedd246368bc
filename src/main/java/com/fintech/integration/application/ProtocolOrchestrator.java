package com.fintech.integration.application;

import com.fintech.integration.domain.RiskEvaluation;
import com.fintech.integration.domain.UserManagement;
import com.fintech.integration.infrastructure.grpc.RiskEvaluationGrpcService;
import com.fintech.integration.infrastructure.grpc.UserManagementGrpcService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProtocolOrchestrator {

    private static final Logger log = LoggerFactory.getLogger(ProtocolOrchestrator.class);
    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    private final RiskEvaluationGrpcService riskEvaluationGrpcService;
    private final UserManagementGrpcService userManagementGrpcService;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final Map<String, ProtocolSelector> protocolSelectors;

    public ProtocolOrchestrator(
            RiskEvaluationGrpcService riskEvaluationGrpcService,
            UserManagementGrpcService userManagementGrpcService,
            CircuitBreakerRegistry circuitBreakerRegistry) {
        this.riskEvaluationGrpcService = riskEvaluationGrpcService;
        this.userManagementGrpcService = userManagementGrpcService;
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.protocolSelectors = initializeSelectors();
        log.info("ProtocolOrchestrator inicializado con {} selectores de protocolo", protocolSelectors.size());
    }

    private Map<String, ProtocolSelector> initializeSelectors() {
        Map<String, ProtocolSelector> selectors = new ConcurrentHashMap<>();
        selectors.put("risk-evaluation", new ProtocolSelector(CommunicationProtocol.GRPC, CommunicationProtocol.AMQP));
        selectors.put("user-management", new ProtocolSelector(CommunicationProtocol.GRPC, CommunicationProtocol.GRAPHQL));
        selectors.put("notifications", new ProtocolSelector(CommunicationProtocol.AMQP, CommunicationProtocol.GRPC));
        return selectors;
    }

    public Mono<RiskEvaluation> evaluateRisk(RiskEvaluation evaluation, String correlationId) {
        log.info("Iniciando evaluación de riesgo con correlationId={}", correlationId);
        validateCorrelationId(correlationId);
        evaluation.validate();
        
        ProtocolSelector selector = protocolSelectors.get("risk-evaluation");
        CommunicationProtocol protocol = selectProtocol(selector, evaluation);
        
        log.info("Protocolo seleccionado para evaluación de riesgo: {} con correlationId={}", protocol, correlationId);
        
        return executeWithResilience(
            () -> riskEvaluationGrpcService.evaluateRisk(evaluation, correlationId),
            "risk-evaluation",
            protocol
        );
    }

    public Mono<UserManagement> manageUser(UserManagement user, String correlationId) {
        log.info("Iniciando gestión de usuario con correlationId={}", correlationId);
        validateCorrelationId(correlationId);
        user.validate();
        
        ProtocolSelector selector = protocolSelectors.get("user-management");
        CommunicationProtocol protocol = selectProtocol(selector, user);
        
        log.info("Protocolo seleccionado para gestión de usuario: {} con correlationId={}", protocol, correlationId);
        
        return executeWithResilience(
            () -> userManagementGrpcService.manageUser(user, correlationId),
            "user-management",
            protocol
        );
    }

    public Flux<RiskEvaluation> streamRiskEvaluations(String correlationId) {
        log.info("Iniciando stream de evaluaciones de riesgo con correlationId={}", correlationId);
        validateCorrelationId(correlationId);
        
        return riskEvaluationGrpcService.streamRiskEvaluations(correlationId)
            .doOnSubscribe(s -> log.debug("Suscrito a stream de evaluaciones de riesgo"))
            .doOnComplete(() -> log.info("Stream de evaluaciones de riesgo completado"))
            .doOnError(e -> log.error("Error en stream de evaluaciones de riesgo: {}", e.getMessage()));
    }

    public Flux<UserManagement> streamUserUpdates(String correlationId) {
        log.info("Iniciando stream de actualizaciones de usuario con correlationId={}", correlationId);
        validateCorrelationId(correlationId);
        
        return userManagementGrpcService.streamUserUpdates(correlationId)
            .doOnSubscribe(s -> log.debug("Suscrito a stream de actualizaciones de usuario"))
            .doOnComplete(() -> log.info("Stream de actualizaciones de usuario completado"))
            .doOnError(e -> log.error("Error en stream de actualizaciones de usuario: {}", e.getMessage()));
    }

    private <T> Mono<T> executeWithResilience(
            java.util.function.Supplier<Mono<T>> operation,
            String serviceName,
            CommunicationProtocol protocol) {
        
        CircuitBreaker circuitBreaker = getCircuitBreaker(serviceName, protocol);
        
        return Mono.defer(() -> operation.get())
            .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
            .doOnSuccess(result -> log.info("Operación {} completada exitosamente con protocolo {}", serviceName, protocol))
            .doOnError(error -> log.error("Error en operación {} con protocolo {}: {}", serviceName, protocol, error.getMessage()));
    }

    private CircuitBreaker getCircuitBreaker(String serviceName, CommunicationProtocol protocol) {
        String circuitBreakerName = serviceName + "-" + protocol.name().toLowerCase();
        
        return circuitBreakerRegistry.circuitBreaker(circuitBreakerName, cb -> cb
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofSeconds(30))
            .slidingWindowSize(10)
            .minimumNumberOfCalls(5)
            .permittedNumberOfCallsInHalfOpenState(3)
            .automaticTransitionFromOpenToHalfOpenEnabled(true)
        );
    }

    private CommunicationProtocol selectProtocol(ProtocolSelector selector, Object domainObject) {
        if (domainObject instanceof RiskEvaluation) {
            RiskEvaluation evaluation = (RiskEvaluation) domainObject;
            int riskLevel = evaluation.riskLevelToNumeric();
            if (riskLevel >= 8) {
                return selector.getPrimary();
            }
            return selector.getSecondary();
        }
        
        if (domainObject instanceof UserManagement) {
            UserManagement user = (UserManagement) domainObject;
            if (user.hasRole("ADMIN") || user.hasRole("SUPER_ADMIN")) {
                return selector.getPrimary();
            }
            return selector.getSecondary();
        }
        
        return selector.getPrimary();
    }

    private void validateCorrelationId(String correlationId) {
        if (correlationId == null || correlationId.isBlank()) {
            throw new IllegalArgumentException("El correlationId no puede ser nulo o vacío");
        }
    }

    public enum CommunicationProtocol {
        GRPC,
        AMQP,
        GRAPHQL,
        REST
    }

    private static class ProtocolSelector {
        private final CommunicationProtocol primary;
        private final CommunicationProtocol secondary;

        public ProtocolSelector(CommunicationProtocol primary, CommunicationProtocol secondary) {
            this.primary = primary;
            this.secondary = secondary;
        }

        public CommunicationProtocol getPrimary() {
            return primary;
        }

        public CommunicationProtocol getSecondary() {
            return secondary;
        }
    }

    @FunctionalInterface
    private interface Supplier<T> {
        T get();
    }
}