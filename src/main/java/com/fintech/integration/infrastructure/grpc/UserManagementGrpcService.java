package com.fintech.integration.infrastructure.grpc;

import com.fintech.integration.domain.UserManagement;
import io.grpc.ManagedChannel;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserManagementGrpcService {

    private static final Logger log = LoggerFactory.getLogger(UserManagementGrpcService.class);
    private static final String SERVICE_NAME = "UserManagementGrpcService";

    private final ManagedChannel userManagementChannel;
    private final GrpcAsyncServiceStub asyncStub;
    private final GrpcBlockingServiceStub blockingStub;

    public UserManagementGrpcService(
            @Qualifier("userManagementChannel") ManagedChannel userManagementChannel) {
        this.userManagementChannel = userManagementChannel;
        this.asyncStub = new GrpcAsyncServiceStub(userManagementChannel);
        this.blockingStub = new GrpcBlockingServiceStub(userManagementChannel);
        log.info("UserManagementGrpcService inicializado con canal hacia servicio de gestión de usuarios");
    }

    public Mono<UserManagement> manageUser(UserManagement user, String correlationId) {
        log.info("Gestionando usuario via gRPC con correlationId={}, username={}", 
            correlationId, user.userId());
        
        user.validate();
        
        return Mono.fromCallable(() -> {
            try {
                UserManagementRequest request = buildRequest(user, correlationId);
                UserManagementResponse response = blockingStub.manageUser(request);
                return mapResponseToDomain(response);
            } catch (Exception e) {
                log.error("Error en gestión de usuario gRPC: {}", e.getMessage());
                throw new RuntimeException("Falló la gestión de usuario via gRPC", e);
            }
        }).subscribeOn(Schedulers.boundedElastic())
          .doOnSuccess(result -> log.info("Gestión de usuario completada exitosamente para {}", result.userId()))
          .doOnError(error -> log.error("Gestión de usuario fallida: {}", error.getMessage()));
    }

    public Flux<UserManagement> streamUserUpdates(String correlationId) {
        log.info("Iniciando streaming de actualizaciones de usuario con correlationId={}", correlationId);
        
        return Flux.create(sink -> {
            AtomicBoolean completed = new AtomicBoolean(false);
            
            try {
                StreamObserver<UserManagementRequest> requestObserver = 
                    asyncStub.streamUserUpdates(new StreamObserver<UserManagementResponse>() {
                        @Override
                        public void onNext(UserManagementResponse response) {
                            UserManagement user = mapResponseToDomain(response);
                            sink.next(user);
                            log.debug("Recibido stream de usuario: {}", user.userId());
                        }

                        @Override
                        public void onError(Throwable t) {
                            log.error("Error en stream de usuarios: {}", t.getMessage());
                            sink.error(t);
                        }

                        @Override
                        public void onCompleted() {
                            log.info("Stream de actualizaciones de usuario completado");
                            completed.set(true);
                            sink.complete();
                        }
                    });

                UserManagementRequest initialRequest = UserManagementRequest.newBuilder()
                    .setCorrelationId(correlationId)
                    .setRequestId(java.util.UUID.randomUUID().toString())
                    .setOperationType(UserOperationType.SUBSCRIBE_UPDATES)
                    .build();
                
                requestObserver.onNext(initialRequest);
                requestObserver.onCompleted();

            } catch (Exception e) {
                log.error("Error al iniciar streaming de usuarios: {}", e.getMessage());
                sink.error(e);
            }

            sink.onDispose(() -> {
                log.info("Desuscribiendo del stream de actualizaciones de usuario");
                if (!completed.get()) {
                    userManagementChannel.shutdown();
                }
            });
        });
    }

    public Mono<UserManagement> createUser(UserManagement user, String correlationId) {
        log.info("Creando usuario via gRPC con correlationId={}", correlationId);
        
        return Mono.fromCallable(() -> {
            try {
                UserManagementRequest request = UserManagementRequest.newBuilder()
                    .setCorrelationId(correlationId)
                    .setRequestId(java.util.UUID.randomUUID().toString())
                    .setUserId(user.userId())
                    .setEmail(user.email())
                    .setFullName(user.fullName())
                    .setOperationType(UserOperationType.CREATE)
                    .build();
                
                UserManagementResponse response = blockingStub.manageUser(request);
                return mapResponseToDomain(response);
            } catch (Exception e) {
                log.error("Error al crear usuario via gRPC: {}", e.getMessage());
                throw new RuntimeException("Falló la creación de usuario via gRPC", e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<UserManagement> updateUser(UserManagement user, String correlationId) {
        log.info("Actualizando usuario via gRPC con correlationId={}", correlationId);
        
        return Mono.fromCallable(() -> {
            try {
                UserManagementRequest request = UserManagementRequest.newBuilder()
                    .setCorrelationId(correlationId)
                    .setRequestId(java.util.UUID.randomUUID().toString())
                    .setUserId(user.userId())
                    .setEmail(user.email())
                    .setFullName(user.fullName())
                    .setOperationType(UserOperationType.UPDATE)
                    .build();
                
                UserManagementResponse response = blockingStub.manageUser(request);
                return mapResponseToDomain(response);
            } catch (Exception e) {
                log.error("Error al actualizar usuario via gRPC: {}", e.getMessage());
                throw new RuntimeException("Falló la actualización de usuario via gRPC", e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Boolean> deleteUser(String userId, String correlationId) {
        log.info("Eliminando usuario {} via gRPC con correlationId={}", userId, correlationId);
        
        return Mono.fromCallable(() -> {
            try {
                UserManagementRequest request = UserManagementRequest.newBuilder()
                    .setCorrelationId(correlationId)
                    .setRequestId(java.util.UUID.randomUUID().toString())
                    .setUserId(userId)
                    .setOperationType(UserOperationType.DELETE)
                    .build();
                
                UserManagementResponse response = blockingStub.manageUser(request);
                return response.getSuccess();
            } catch (Exception e) {
                log.error("Error al eliminar usuario via gRPC: {}", e.getMessage());
                throw new RuntimeException("Falló la eliminación de usuario via gRPC", e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<UserManagement> authenticateUser(String username, String password, String correlationId) {
        log.info("Autenticando usuario via gRPC con correlationId={}", correlationId);
        
        return Mono.fromCallable(() -> {
            try {
                AuthenticationRequest request = AuthenticationRequest.newBuilder()
                    .setCorrelationId(correlationId)
                    .setRequestId(java.util.UUID.randomUUID().toString())
                    .setUsername(username)
                    .setPassword(password)
                    .build();
                
                AuthenticationResponse response = blockingStub.authenticate(request);
                if (!response.getAuthenticated()) {
                    throw new SecurityException("Autenticación fallida para usuario: " + username);
                }
                
                return new UserManagement(
                    response.getUserId(),
                    response.getEmail(),
                    response.getFullName(),
                    java.util.List.of(response.getRolesList())
                );
            } catch (Exception e) {
                log.error("Error en autenticación de usuario via gRPC: {}", e.getMessage());
                throw new RuntimeException("Falló la autenticación de usuario via gRPC", e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private UserManagementRequest buildRequest(UserManagement user, String correlationId) {
        return UserManagementRequest.newBuilder()
            .setCorrelationId(correlationId)
            .setRequestId(java.util.UUID.randomUUID().toString())
            .setUserId(user.userId())
            .setEmail(user.email())
            .setFullName(user.fullName())
            .setOperationType(UserOperationType.UPDATE)
            .build();
    }

    private UserManagement mapResponseToDomain(UserManagementResponse response) {
        return new UserManagement(
            response.getUserId(),
            response.getEmail(),
            response.getFullName(),
            response.getRolesList()
        );
    }

    public void shutdown() {
        try {
            userManagementChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            log.info("Canal de UserManagement gRPC cerrado");
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

        UserManagementResponse manageUser(UserManagementRequest request) {
            return (UserManagementResponse) ClientCalls.blockingUnaryCall(
                channel, 
                null, 
                request, 
                null
            );
        }

        AuthenticationResponse authenticate(AuthenticationRequest request) {
            return (AuthenticationResponse) ClientCalls.blockingUnaryCall(
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

        <Req, Resp> StreamObserver<Req> streamUserUpdates(StreamObserver<Resp> responseObserver) {
            return ClientCalls.asyncServerStreamingCall(
                channel,
                null,
                null,
                responseObserver
            );
        }
    }

    private static class UserManagementRequest {
        private String correlationId;
        private String requestId;
        private String userId;
        private String email;
        private String fullName;
        private UserOperationType operationType;

        public static Builder newBuilder() {
            return new Builder();
        }

        public String getCorrelationId() { return correlationId; }
        public String getRequestId() { return requestId; }
        public String getUserId() { return userId; }
        public String getEmail() { return email; }
        public String getFullName() { return fullName; }
        public UserOperationType getOperationType() { return operationType; }

        public static class Builder {
            private UserManagementRequest request = new UserManagementRequest();

            public Builder setCorrelationId(String correlationId) {
                request.correlationId = correlationId;
                return this;
            }

            public Builder setRequestId(String requestId) {
                request.requestId = requestId;
                return this;
            }

            public Builder setUserId(String userId) {
                request.userId = userId;
                return this;
            }

            public Builder setEmail(String email) {
                request.email = email;
                return this;
            }

            public Builder setFullName(String fullName) {
                request.fullName = fullName;
                return this;
            }

            public Builder setOperationType(UserOperationType type) {
                request.operationType = type;
                return this;
            }

            public UserManagementRequest build() {
                return request;
            }
        }
    }

    private enum UserOperationType {
        CREATE,
        UPDATE,
        DELETE,
        SUBSCRIBE_UPDATES
    }

    private static class UserManagementResponse {
        private String userId;
        private String email;
        private String fullName;
        private java.util.List<String> roles;
        private boolean success;

        public String getUserId() { return userId; }
        public String getEmail() { return email; }
        public String getFullName() { return fullName; }
        public java.util.List<String> getRolesList() { return roles; }
        public boolean getSuccess() { return success; }
    }

    private static class AuthenticationRequest {
        private String correlationId;
        private String requestId;
        private String username;
        private String password;

        public static Builder newBuilder() {
            return new Builder();
        }

        public String getCorrelationId() { return correlationId; }
        public String getRequestId() { return requestId; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }

        public static class Builder {
            private AuthenticationRequest request = new AuthenticationRequest();

            public Builder setCorrelationId(String correlationId) {
                request.correlationId = correlationId;
                return this;
            }

            public Builder setRequestId(String requestId) {
                request.requestId = requestId;
                return this;
            }

            public Builder setUsername(String username) {
                request.username = username;
                return this;
            }

            public Builder setPassword(String password) {
                request.password = password;
                return this;
            }

            public AuthenticationRequest build() {
                return request;
            }
        }
    }

    private static class AuthenticationResponse {
        private boolean authenticated;
        private String userId;
        private String email;
        private String fullName;
        private java.util.List<String> roles;

        public boolean getAuthenticated() { return authenticated; }
        public String getUserId() { return userId; }
        public String getEmail() { return email; }
        public String getFullName() { return fullName; }
        public java.util.List<String> getRolesList() { return roles; }
    }
}