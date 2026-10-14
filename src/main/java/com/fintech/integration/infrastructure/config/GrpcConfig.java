package com.fintech.integration.infrastructure.config;




import com.fintech.integration.application.Supplier;
import com.fintech.integration.domain.UserManagement;
import com.fintech.integration.domain.RiskEvaluation;
import com.fintech.integration.IntegrationApplication;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class GrpcConfig {

    private static final Logger log = LoggerFactory.getLogger(GrpcConfig.class);

    @Value("${grpc.risk-evaluation.host:localhost}")
    private String riskEvaluationHost;

    @Value("${grpc.risk-evaluation.port:9090}")
    private int riskEvaluationPort;

    @Value("${grpc.user-management.host:localhost}")
    private String userManagementHost;

    @Value("${grpc.user-management.port:9091}")
    private int userManagementPort;

    @Value("${grpc.connection-timeout:5000}")
    private long connectionTimeout;

    @Value("${grpc.max-inbound-message-size:10485760}")
    private int maxInboundMessageSize;

    @Bean
    public ManagedChannel riskEvaluationChannel(IntegrationApplication integrationApp) {
        log.info("Configurando canal gRPC para RiskEvaluation en {}:{}", riskEvaluationHost, riskEvaluationPort);
        return integrationApp.riskEvaluationChannel();
    }

    @Bean
    public ManagedChannel userManagementChannel(IntegrationApplication integrationApp) {
        log.info("Configurando canal gRPC para UserManagement en {}:{}", userManagementHost, userManagementPort);
        return integrationApp.userManagementChannel();
    }

    @Bean
    public CircuitBreakerConfig grpcCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .slowCallRateThreshold(50)
                .slowCallDurationThreshold(Duration.ofSeconds(3))
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .minimumNumberOfCalls(10)
                .build();
    }

    @Bean
    public CircuitBreakerRegistry grpcCircuitBreakerRegistry(CircuitBreakerConfig grpcCircuitBreakerConfig) {
        return CircuitBreakerRegistry.of(grpcCircuitBreakerConfig);
    }

    @Bean
    public CircuitBreaker riskEvaluationCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("riskEvaluationService");
        log.info("CircuitBreaker configurado para RiskEvaluationService");
        return circuitBreaker;
    }

    @Bean
    public CircuitBreaker userManagementCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("userManagementService");
        log.info("CircuitBreaker configurado para UserManagementService");
        return circuitBreaker;
    }

    @Bean
    public io.grpc.interceptor.MetadataInterceptor riskEvaluationMetadataInterceptor() {
        return new io.grpc.interceptor.MetadataInterceptor(
            io.grpc.Metadata.of("correlation-id", io.grpc.Metadata.AsciiMetadata.
                of("corr-" + System.currentTimeMillis()))
        );
    }

    @Bean
    public io.grpc.interceptor.MetadataInterceptor userManagementMetadataInterceptor() {
        return new io.grpc.interceptor.MetadataInterceptor(
            io.grpc.Metadata.of("correlation-id", io.grpc.Metadata.AsciiMetadata.
                of("corr-" + System.currentTimeMillis()))
        );
    }

    public static ManagedChannel createChannelWithResilience(
            String host,
            int port,
            long timeout,
            int maxMessageSize,
            @Nullable CircuitBreaker circuitBreaker) {
        
        ManagedChannelBuilder<?> channelBuilder = NettyChannelBuilder.forAddress(host, port)
                .maxInboundMessageSize(maxMessageSize)
                .keepAliveTime(30, TimeUnit.SECONDS)
                .keepAliveTimeout(10, TimeUnit.SECONDS)
                .enableKeepAlive(true)
                .negotiationType(NegotiationType.PLAINTEXT);

        if (timeout > 0) {
            channelBuilder.withOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout);
        }

        if (circuitBreaker != null) {
            log.info("CircuitBreaker habilitado para canal gRPC {}:{}", host, port);
        }

        return channelBuilder.build();
    }

    public static <T> T executeWithCircuitBreaker(
            CircuitBreaker circuitBreaker,
            java.util.function.Supplier<T> operation) {
        
        return io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator
                .of(circuitBreaker)
                .apply(operation);
    }
}