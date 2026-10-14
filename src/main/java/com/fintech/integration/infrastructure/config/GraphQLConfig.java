package com.fintech.integration.infrastructure.config;

import com.fintech.integration.domain.RiskEvaluation;
import com.fintech.integration.domain.UserManagement;
import com.fintech.integration.domain.Notification;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.schema.DataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeRuntimeWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
            .type("Query", typeWiring -> typeWiring
                .dataFetcher("riskEvaluation", riskEvaluationDataFetcher())
                .dataFetcher("userManagement", userManagementDataFetcher())
                .dataFetcher("notifications", notificationsDataFetcher())
            )
            .type("Mutation", typeWiring -> typeWiring
                .dataFetcher("createRiskEvaluation", createRiskEvaluationDataFetcher())
                .dataFetcher("updateUserManagement", updateUserManagementDataFetcher())
                .dataFetcher("sendNotification", sendNotificationDataFetcher())
            )
            .type("Subscription", typeWiring -> typeWiring
                .dataFetcher("riskEvaluationUpdates", riskEvaluationSubscriptionDataFetcher())
                .dataFetcher("userManagementUpdates", userManagementSubscriptionDataFetcher())
            )
            .scalar(GraphQLScalars.graphQLLongScalar())
            .scalar(GraphQLScalars.graphQLBigDecimalScalar());
    }

    @Bean
    public DataFetcher<Mono<RiskEvaluation>> riskEvaluationDataFetcher() {
        return environment -> {
            String evaluationId = environment.getArgument("evaluationId");
            String userId = environment.getArgument("userId");
            return Mono.fromCallable(() -> new RiskEvaluation(
                evaluationId,
                userId,
                "HIGH",
                85,
                List.of("credit_score", "income_verification"),
                Map.of("source", "graphql")
            ));
        };
    }

    @Bean
    public DataFetcher<Mono<UserManagement>> userManagementDataFetcher() {
        return environment -> {
            String userId = environment.getArgument("userId");
            return Mono.fromCallable(() -> new UserManagement(
                userId,
                "john.doe@example.com",
                "John Doe",
                List.of("USER", "PREMIUM"),
                true,
                Map.of("source", "graphql")
            ));
        };
    }

    @Bean
    public DataFetcher<Mono<List<Notification>>> notificationsDataFetcher() {
        return environment -> {
            String userId = environment.getArgument("userId");
            return Mono.fromCallable(() -> List.of(
                new Notification(
                    "notif-001",
                    userId,
                    "RISK_ALERT",
                    "Su evaluación de riesgo ha sido procesada",
                    Map.of("priority", "high")
                ),
                new Notification(
                    "notif-002",
                    userId,
                    "WELCOME",
                    "Bienvenido al sistema",
                    Map.of("priority", "normal")
                )
            ));
        };
    }

    @Bean
    public DataFetcher<Mono<RiskEvaluation>> createRiskEvaluationDataFetcher() {
        return environment -> {
            Map<String, Object> input = environment.getArgument("input");
            String userId = (String) input.get("userId");
            String evaluationType = (String) input.get("evaluationType");
            return Mono.fromCallable(() -> new RiskEvaluation(
                "eval-" + System.currentTimeMillis(),
                userId,
                evaluationType,
                75,
                List.of("automated_check"),
                Map.of("source", "graphql-mutation", "created_by", "graphql")
            ));
        };
    }

    @Bean
    public DataFetcher<Mono<UserManagement>> updateUserManagementDataFetcher() {
        return environment -> {
            String userId = environment.getArgument("userId");
            Map<String, Object> updates = environment.getArgument("updates");
            return Mono.fromCallable(() -> new UserManagement(
                userId,
                (String) updates.getOrDefault("email", "updated@example.com"),
                (String) updates.getOrDefault("name", "Updated Name"),
                List.of("USER"),
                (Boolean) updates.getOrDefault("active", true),
                Map.of("source", "graphql-mutation")
            ));
        };
    }

    @Bean
    public DataFetcher<Mono<Notification>> sendNotificationDataFetcher() {
        return environment -> {
            Map<String, Object> input = environment.getArgument("input");
            return Mono.fromCallable(() -> new Notification(
                "notif-" + System.currentTimeMillis(),
                (String) input.get("userId"),
                (String) input.get("type"),
                (String) input.get("message"),
                Map.of("source", "graphql-mutation", "priority", "normal")
            ));
        };
    }

    @Bean
    public DataFetcher<org.reactivestreams.Publisher<RiskEvaluation>> riskEvaluationSubscriptionDataFetcher() {
        return environment -> {
            String userId = environment.getArgument("userId");
            return org.reactivestreams.Publisher<RiskEvaluation> subscriber -> {
                new Thread(() -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            Thread.sleep(1000);
                            subscriber.onNext(new RiskEvaluation(
                                "eval-stream-" + i,
                                userId,
                                i % 2 == 0 ? "HIGH" : "MEDIUM",
                                50 + (i * 10),
                                List.of("stream_update"),
                                Map.of("iteration", String.valueOf(i))
                            ));
                        } catch (InterruptedException e) {
                            subscriber.onError(e);
                            return;
                        }
                    }
                    subscriber.onComplete();
                }).start();
            };
        };
    }

    @Bean
    public DataFetcher<org.reactivestreams.Publisher<UserManagement>> userManagementSubscriptionDataFetcher() {
        return environment -> {
            String userId = environment.getArgument("userId");
            return org.reactivestreams.Publisher<UserManagement> subscriber -> {
                new Thread(() -> {
                    String[] roles = {"USER", "PREMIUM", "ADMIN", "SUPER_ADMIN"};
                    for (int i = 0; i < roles.length; i++) {
                        try {
                            Thread.sleep(1500);
                            subscriber.onNext(new UserManagement(
                                userId,
                                "user" + i + "@example.com",
                                "User " + i,
                                List.of(roles[i]),
                                true,
                                Map.of("iteration", String.valueOf(i))
                            ));
                        } catch (InterruptedException e) {
                            subscriber.onError(e);
                            return;
                        }
                    }
                    subscriber.onComplete();
                }).start();
            };
        };
    }

    @Bean
    public DataFetcherExceptionResolver dataFetcherExceptionResolver() {
        return errors -> errors.stream()
            .map(this::mapToGraphQLError)
            .collect(Collectors.toList());
    }

    private GraphQLError mapToGraphQLError(Throwable error) {
        if (error instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching ewdf = (ExceptionWhileDataFetching) error;
            return new GraphQLError() {
                @Override
                public String getMessage() {
                    return "Error fetching data: " + ewdf.getException().getMessage();
                }

                @Override
                public List<Object> getPath() {
                    return ewdf.getPath();
                }

                @Override
                public Map<String, Object> toSpecification() {
                    return Map.of(
                        "errorType", "DataFetchingException",
                        "message", ewdf.getException().getMessage(),
                        "locations", List.of()
                    );
                }

                @Override
                public Map<String, Object> getExtensions() {
                    return Map.of(
                        "errorCode", "DATA_FETCHING_ERROR",
                        "timestamp", System.currentTimeMillis()
                    );
                }
            };
        }
        return new GraphQLError() {
            @Override
            public String getMessage() {
                return error.getMessage();
            }

            @Override
            public Map<String, Object> toSpecification() {
                return Map.of(
                    "errorType", error.getClass().getSimpleName(),
                    "message", error.getMessage()
                );
            }

            @Override
            public Map<String, Object> getExtensions() {
                return Map.of(
                    "errorCode", "INTERNAL_ERROR",
                    "timestamp", System.currentTimeMillis()
                );
            }
        };
    }
}