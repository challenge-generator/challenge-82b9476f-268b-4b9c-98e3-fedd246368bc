package com.fintech.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import reactor.core.publisher.Hooks;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import com.fintech.integration.infrastructure.config.GrpcConfig;
import com.fintech.integration.infrastructure.config.AmqpConfig;
import com.fintech.integration.application.ProtocolOrchestrator;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import java.time.Duration;

@SpringBootApplication
@EnableAsync
@ConfigurationPropertiesScan
public class IntegrationApplication {
    
    @Value("${grpc.risk-evaluation.host}")
    private String riskEvaluationHost;
    
    @Value("${grpc.risk-evaluation.port}")
    private int riskEvaluationPort;
    
    @Value("${grpc.user-management.host}")
    private String userManagementHost;
    
    @Value("${grpc.user-management.port}")
    private int userManagementPort;
    
    @Value("${amqp.notifications.queue}")
    private String notificationsQueue;
    
    @Value("${amqp.notifications.exchange}")
    private String notificationsExchange;
    
    @Value("${amqp.notifications.routing-key}")
    private String notificationsRoutingKey;

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(IntegrationApplication.class, args);
    }
    
    @Bean
    public ManagedChannel riskEvaluationChannel() {
        return ManagedChannelBuilder.forAddress(riskEvaluationHost, riskEvaluationPort)
                .usePlaintext()
                .build();
    }
    
    @Bean
    public ManagedChannel userManagementChannel() {
        return ManagedChannelBuilder.forAddress(userManagementHost, userManagementPort)
                .usePlaintext()
                .build();
    }
    
    @Bean
    public Queue notificationsQueue() {
        return new Queue(notificationsQueue, true);
    }
    
    @Bean
    public TopicExchange notificationsExchange() {
        return new TopicExchange(notificationsExchange);
    }
    
    @Bean
    public Binding notificationsBinding(Queue notificationsQueue, TopicExchange notificationsExchange) {
        return BindingBuilder.bind(notificationsQueue).to(notificationsExchange).with(notificationsRoutingKey);
    }
    
    @Bean
    public CircuitBreaker grpcCircuitBreaker() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .build();
        return CircuitBreaker.of("grpcCircuitBreaker", config);
    }
    
    @Bean
    public CircuitBreaker amqpCircuitBreaker() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(30)
                .waitDurationInOpenState(Duration.ofMillis(500))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(3)
                .build();
        return CircuitBreaker.of("amqpCircuitBreaker", config);
    }
    
    @Bean
    public ProtocolOrchestrator protocolOrchestrator(
            GrpcConfig grpcConfig,
            AmqpConfig amqpConfig,
            CircuitBreaker grpcCircuitBreaker,
            CircuitBreaker amqpCircuitBreaker) {
        return new ProtocolOrchestrator(grpcConfig, amqpConfig, grpcCircuitBreaker, amqpCircuitBreaker);
    }
}