package com.fintech.integration.infrastructure.grpc;

import com.fintech.integration.domain.RiskEvaluation;
import io.grpc.ManagedChannel;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

@Service
public class RiskEvaluationGrpcService {

    private static final Logger log = LoggerFactory.getLogger(RiskEvaluationGrpcService.class);
    private static final String SERVICE_NAME = "RiskEvaluationGrpcService";

    private final ManagedChannel riskEvaluationChannel;
    private final GrpcAsyncServiceStub asyncStub;
    private final GrpcBlockingServiceStub blockingStub;

    public RiskEvaluationGrpcService(
            @Qualifier("riskEvaluationChannel") ManagedChannel riskEvaluationChannel) {
        this.riskEvaluationChannel = riskEvaluationChannel;
        this.asyncStub = new GrpcAsyncServiceStub(riskEvaluationChannel);
        this.blockingStub = new GrpcBlockingServiceStub(riskEvaluationChannel);
        log.info("RiskEvaluationGrpcService inicializado con canal hacia servicio de evaluación de riesgos");
    }

    public Mono<RiskEvaluation> evaluateRisk(RiskEvaluation evaluation, String correlationId) {
        log.info("Evaluando riesgo via gRPC con correlationId={}, nivel={}", 
            correlationId, evaluation.riskLevelToNumeric());
        
        return Mono.fromCallable(() -> {
            try {
                RiskEvaluationRequest request = buildRequest(evaluation, correlationId);
                RiskEvaluationResponse response = blockingStub.evaluateRisk(request);
                return mapResponseToDomain(response);
            } catch (Exception e) {
                log.error("Error en evaluación de riesgo gRPC: {}", e.getMessage());
                throw new RuntimeException("Falló la evaluación de riesgo via gRPC", e);
            }
        }).subscribeOn(Schedulers.boundedElastic())
          .doOnSuccess(result -> log.info("Evaluación de riesgo completada exitosamente"))
          .doOnError(error -> log.error("Evaluación de riesgo fallida: {}", error.getMessage()));
    }

    public Flux<RiskEvaluation> streamRiskEvaluations(String correlationId) {
        log.info("Iniciando streaming de evaluaciones de riesgo con correlationId={}", correlationId);
        
        return Flux.create(sink -> {
            AtomicBoolean completed = new AtomicBoolean(false);
            
            try {
                StreamObserver<RiskEvaluationRequest> requestObserver = 
                    asyncStub.streamRiskEvaluations(new StreamObserver<RiskEvaluationResponse>() {
                        @Override
                        public void onNext(RiskEvaluationResponse response) {
                            RiskEvaluation evaluation = mapResponseToDomain(response);
                            sink.next(evaluation);
                            log.debug("Recibido stream de evaluación de riesgo: nivel={}", 
                                evaluation.riskLevelToNumeric());
                        }

                        @Override
                        public void onError(Throwable t) {
                            log.error("Error en stream de evaluaciones: {}", t.getMessage());
                            sink.error(t);
                        }

                        @Override
                        public void onCompleted() {
                            log.info("Stream de evaluaciones de riesgo completado");
                            completed.set(true);
                            sink.complete();
                        }
                    });

                RiskEvaluationRequest initialRequest = RiskEvaluationRequest.newBuilder()
                    .setCorrelationId(correlationId)
                    .setRequestId(java.util.UUID.randomUUID().toString())
                    .build();
                
                requestObserver.onNext(initialRequest);
                requestObserver.onCompleted();

            } catch (Exception e) {
                log.error("Error al iniciar streaming: {}", e.getMessage());
                sink.error(e);
            }

            sink.onDispose(() -> {
                log.info("Desuscribiendo del stream de evaluaciones de riesgo");
                if (!completed.get()) {
                    riskEvaluationChannel.shutdown();
                }
            });
        });
    }

    public Mono<RiskEvaluation> evaluateRiskBatch(RiskEvaluation evaluation, String correlationId) {
        log.info("Evaluación por lotes de riesgo via gRPC con correlationId={}", correlationId);
        
        return Flux.fromIterable(evaluation.riskLevelToNumeric() > 5 ? 
                java.util.List.of(evaluation) : java.util.List.of())
            .flatMap(eval -> evaluateRisk(eval, correlationId))
            .singleOrEmpty();
    }

    public Mono<Void> healthCheck(String correlationId) {
        log.info("Ejecutando health check del servicio de evaluación de riesgos");
        
        return Mono.fromCallable(() -> {
            HealthCheckRequest request = HealthCheckRequest.newBuilder()
                .setServiceName(SERVICE_NAME)
                .setCorrelationId(correlationId)
                .build();
            
            HealthCheckResponse response = blockingStub.healthCheck(request);
            if (!response.getHealthy()) {
                throw new RuntimeException("Health check falló: " + response.getMessage());
            }
            return Void.class;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private RiskEvaluationRequest buildRequest(RiskEvaluation evaluation, String correlationId) {
        return RiskEvaluationRequest.newBuilder()
            .setCorrelationId(correlationId)
            .setRequestId(java.util.UUID.randomUUID().toString())
            .setAmount(evaluation.riskLevelToNumeric() * 10000.0)
            .setClientId("client-" + evaluation.riskLevelToNumeric())
            .setEvaluationType(RiskEvaluationType.STANDARD)
            .build();
    }

    private RiskEvaluation mapResponseToDomain(RiskEvaluationResponse response) {
        return new RiskEvaluation(
            response.getRiskId(),
            response.getRiskLevel(),
            response.getApproved(),
            response.getMessage()
        );
    }

    public void shutdown() {
        try {
            riskEvaluationChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            log.info("Canal de RiskEvaluation gRPC cerrado");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Error al cerrar el canal de gRPC", e);
        }
    }

    private static class GrpcBlockingServiceStub {
        private final ManagedChannel channel;

        GrpcBlockingServiceStub(ManagedChannel channel) {
            this.channel = channel;
        }

        RiskEvaluationResponse evaluateRisk(RiskEvaluationRequest request) {
            return (RiskEvaluationResponse) ClientCalls.blockingUnaryCall(
                channel, 
                null, 
                request, 
                null
            );
        }

        HealthCheckResponse healthCheck(HealthCheckRequest request) {
            return (HealthCheckResponse) ClientCalls.blockingUnaryCall(
                channel,
                null,
                request,
                null
            );
        }
    }

    private static class GrpcAsyncServiceStub {
        private final ManagedChannel channel;

        GrpcAsyncServiceStub(ManagedChannel channel) {
            this.channel = channel;
        }

        <Req, Resp> StreamObserver<Req> streamRiskEvaluations(StreamObserver<Resp> responseObserver) {
            return ClientCalls.asyncServerStreamingCall(
                channel,
                null,
                null,
                responseObserver
            );
        }
    }

    private static class RiskEvaluationRequest {
        private String correlationId;
        private String requestId;
        private double amount;
        private String clientId;
        private RiskEvaluationType evaluationType;

        public static RiskEvaluationRequest.Builder newBuilder() {
            return new Builder();
        }

        public String getCorrelationId() { return correlationId; }
        public String getRequestId() { return requestId; }
        public double getAmount() { return amount; }
        public String getClientId() { return clientId; }
        public RiskEvaluationType getEvaluationType() { return evaluationType; }

        public static class Builder {
            private RiskEvaluationRequest request = new RiskEvaluationRequest();

            public Builder setCorrelationId(String correlationId) {
                request.correlationId = correlationId;
                return this;
            }

            public Builder setRequestId(String requestId) {
                request.requestId = requestId;
                return this;
            }

            public Builder setAmount(double amount) {
                request.amount = amount;
                return this;
            }

            public Builder setClientId(String clientId) {
                request.clientId = clientId;
                return this;
            }

            public Builder setEvaluationType(RiskEvaluationType type) {
                request.evaluationType = type;
                return this;
            }

            public RiskEvaluationRequest build() {
                return request;
            }
        }
    }

    private enum RiskEvaluationType {
        STANDARD,
        ADVANCED,
        URGENT
    }

    private static class RiskEvaluationResponse {
        private String riskId;
        private int riskLevel;
        private boolean approved;
        private String message;

        public String getRiskId() { return riskId; }
        public int getRiskLevel() { return riskLevel; }
        public boolean getApproved() { return approved; }
        public String getMessage() { return message; }
    }

    private static class HealthCheckRequest {
        private String serviceName;
        private String correlationId;

        public static Builder newBuilder() {
            return new Builder();
        }

        public String getServiceName() { return serviceName; }
        public String getCorrelationId() { return correlationId; }

        public static class Builder {
            private HealthCheckRequest request = new HealthCheckRequest();

            public Builder setServiceName(String serviceName) {
                request.serviceName = serviceName;
                return this;
            }

            public Builder setCorrelationId(String correlationId) {
                request.correlationId = correlationId;
                return this;
            }

            public HealthCheckRequest build() {
                return request;
            }
        }
    }

    private static class HealthCheckResponse {
        private boolean healthy;
        private String message;

        public boolean getHealthy() { return healthy; }
        public String getMessage() { return message; }
    }
}