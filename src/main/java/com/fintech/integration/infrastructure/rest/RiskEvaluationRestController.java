package com.fintech.integration.infrastructure.rest;


import com.fintech.integration.infrastructure.grpc.Builder;
import com.fintech.integration.domain.RiskEvaluation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/risk-evaluations")
public class RiskEvaluationRestController {

    private static final Logger log = LoggerFactory.getLogger(RiskEvaluationRestController.class);
    private final Map<String, RiskEvaluation> evaluationStore = new ConcurrentHashMap<>();
    private final WebClient webClient;

    public RiskEvaluationRestController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    @GetMapping
    public Flux<RiskEvaluation> getAllEvaluations() {
        log.info("Obteniendo todas las evaluaciones de riesgo");
        return Flux.fromIterable(evaluationStore.values().stream()
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<RiskEvaluation>> getEvaluationById(@PathVariable String id) {
        log.info("Buscando evaluación de riesgo con ID: {}", id);
        return Mono.just(evaluationStore.get(id))
                .map(evaluation -> {
                    if (evaluation == null) {
                        return ResponseEntity.notFound().<RiskEvaluation>build();
                    }
                    return ResponseEntity.ok(evaluation);
                });
    }

    @PostMapping
    public Mono<ResponseEntity<RiskEvaluation>> createEvaluation(@RequestBody RiskEvaluation evaluation) {
        log.info("Creando nueva evaluación de riesgo para cliente: {}", evaluation.clientId());
        return Mono.fromRunnable(() -> {
            try {
                evaluation.validate();
                String id = java.util.UUID.randomUUID().toString();
                RiskEvaluation stored = new RiskEvaluation(
                    id,
                    evaluation.clientId(),
                    evaluation.loanAmount(),
                    evaluation.loanTerm(),
                    evaluation.creditScore(),
                    evaluation.riskLevel(),
                    evaluation.assessmentDate(),
                    evaluation.metadata()
                );
                evaluationStore.put(id, stored);
                log.info("Evaluación de riesgo creada exitosamente con ID: {}", id);
            } catch (IllegalArgumentException e) {
                log.error("Error al validar la evaluación de riesgo: {}", e.getMessage());
                throw new IllegalArgumentException(e.getMessage());
            }
        }).then(Mono.just(evaluationStore.get(evaluation.clientId())))
                .map(ResponseEntity::ok)
                .onErrorResume(IllegalArgumentException.class, e ->
                    Mono.just(ResponseEntity.badRequest().build()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<RiskEvaluation>> updateEvaluation(
            @PathVariable String id,
            @RequestBody RiskEvaluation evaluation) {
        log.info("Actualizando evaluación de riesgo con ID: {}", id);
        return Mono.fromRunnable(() -> {
            if (!evaluationStore.containsKey(id)) {
                throw new IllegalArgumentException("Evaluación no encontrada: " + id);
            }
            evaluation.validate();
            evaluationStore.put(id, evaluation);
            log.info("Evaluación de riesgo actualizada exitosamente: {}", id);
        }).then(Mono.just(evaluationStore.get(id)))
                .map(ResponseEntity::ok)
                .onErrorResume(IllegalArgumentException.class, e ->
                    Mono.just(ResponseEntity.notFound().<RiskEvaluation>build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteEvaluation(@PathVariable String id) {
        log.info("Eliminando evaluación de riesgo con ID: {}", id);
        return Mono.fromRunnable(() -> {
            if (!evaluationStore.containsKey(id)) {
                throw new IllegalArgumentException("Evaluación no encontrada: " + id);
            }
            evaluationStore.remove(id);
            log.info("Evaluación de riesgo eliminada exitosamente: {}", id);
        }).then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(IllegalArgumentException.class, e ->
                    Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/health")
    public Mono<Map<String, String>> health() {
        return Mono.just(Map.of("status", "UP", "service", "risk-evaluation-rest"));
    }
}