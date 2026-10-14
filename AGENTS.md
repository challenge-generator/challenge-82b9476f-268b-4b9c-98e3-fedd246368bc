# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Comparativa de protocolos de comunicación en arquitecturas de microservicios**.

| | |
|---|---|
| Tema | Protocolos de comunicación no convencionales |
| Nivel | advanced-l2 |
| Chapter | Integración — Desarrollo |
| Especialidad | Api |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | microservicio reactivo con capas estándar |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `openapi.yaml con paths, schemas y responses completos`
- `policies/ con las politicas del gateway (rate limit, auth, transformacion)`
- `environments/ con la configuracion por ambiente`
- `tests/ con la coleccion de contrato`
- `README.md con el contrato y los codigos de error`

Dependencias:

- org.springframework.boot:spring-boot-starter-webflux 3.5.6
- org.springframework.boot:spring-boot-starter-amqp 3.5.6
- io.grpc:grpc-netty 1.62.2
- io.grpc:grpc-protobuf 1.62.2
- io.grpc:grpc-stub 1.62.2
- com.graphql-java-kickstart:graphql-spring-boot-starter 15.0.0
- io.projectreactor:reactor-core 3.6.3
- io.github.resilience4j:resilience4j-spring-boot2 2.1.0
- org.springframework.boot:spring-boot-starter-test 3.5.6
- io.grpc:grpc-testing 1.62.2
- org.testcontainers:rabbitmq 1.19.7

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `npx --yes @redocly/cli lint openapi.yaml` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `npx --yes @redocly/cli lint openapi.yaml` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Exploración de protocolos de comunicación**: Resumen comparativo de los protocolos de comunicación.
- **Fase 2 — Evaluación de gRPC en microservicios**: Análisis detallado de gRPC en el contexto de microservicios.
- **Fase 3 — Manejo de mensajes y cola en AMQP**: Análisis detallado del manejo de mensajes y cola en AMQP.
- **Fase 4 — Rendimiento y flexibilidad de GraphQL**: Análisis detallado del rendimiento y flexibilidad de GraphQL.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (65)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/fintech/integration/IntegrationApplication.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Hooks pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `reactor.core.scheduler`
      El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `reactor.core.scheduler`
      El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducer.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/config/GrpcConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/config/AmqpConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/config/GraphQLConfig.java` — `graphql.schema`
      El import graphql.schema.DataFetcher pertenece a graphql.schema, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/config/GraphQLConfig.java` — `graphql.schema.idl`
      El import graphql.schema.idl.RuntimeWiring pertenece a graphql.schema.idl, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/infrastructure/config/GraphQLConfig.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java` — `graphql.schema`
      El import graphql.schema.GraphQLSchema pertenece a graphql.schema, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java` — `CommunicationProtocol.name`
      Se invoca `name` sobre `CommunicationProtocol`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java` — `ProtocolSelector.getPrimary`
      Se invoca `getPrimary` sobre `ProtocolSelector`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java` — `ProtocolSelector.getSecondary`
      Se invoca `getSecondary` sobre `ProtocolSelector`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `RiskEvaluationResponse.getHealthy`
      Se invoca `getHealthy` sobre `RiskEvaluationResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `RiskEvaluationResponse.getMessage`
      Se invoca `getMessage` sobre `RiskEvaluationResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `RiskEvaluationResponse.getRiskId`
      Se invoca `getRiskId` sobre `RiskEvaluationResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `RiskEvaluationResponse.getRiskLevel`
      Se invoca `getRiskLevel` sobre `RiskEvaluationResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `RiskEvaluationResponse.getApproved`
      Se invoca `getApproved` sobre `RiskEvaluationResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagement.userId`
      Se invoca `userId` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagement.email`
      Se invoca `email` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagement.fullName`
      Se invoca `fullName` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getSuccess`
      Se invoca `getSuccess` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `GrpcBlockingServiceStub.authenticate`
      Se invoca `authenticate` sobre `GrpcBlockingServiceStub`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getAuthenticated`
      Se invoca `getAuthenticated` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getUserId`
      Se invoca `getUserId` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getEmail`
      Se invoca `getEmail` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getFullName`
      Se invoca `getFullName` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getRolesList`
      Se invoca `getRolesList` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducer.java` — `Notification.priority`
      Se invoca `priority` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducer.java` — `Notification.metadata`
      Se invoca `metadata` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducer.java` — `Notification.toString`
      Se invoca `toString` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java` — `Notification.notificationType`
      Se invoca `notificationType` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java` — `Notification.priority`
      Se invoca `priority` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java` — `Notification.recipient`
      Se invoca `recipient` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java` — `Notification.metadata`
      Se invoca `metadata` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.firstName`
      Se invoca `firstName` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.lastName`
      Se invoca `lastName` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.createdAt`
      Se invoca `createdAt` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.roles`
      Se invoca `roles` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.fullName`
      Se invoca `fullName` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.userId`
      Se invoca `userId` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.email`
      Se invoca `email` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.isActive`
      Se invoca `isActive` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.clientId`
      Se invoca `clientId` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.loanAmount`
      Se invoca `loanAmount` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.loanTerm`
      Se invoca `loanTerm` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.creditScore`
      Se invoca `creditScore` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.riskLevel`
      Se invoca `riskLevel` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.assessmentDate`
      Se invoca `assessmentDate` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.metadata`
      Se invoca `metadata` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java` — `UserGraphQLController.getUserById`
      Se invoca `getUserById` sobre `UserGraphQLController`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java` — `UserManagement.active`
      Se invoca `active` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java` — `UserManagement.roles`
      Se invoca `roles` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (24)

- `pom.xml`
- `src/main/java/com/fintech/integration/IntegrationApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/fintech/integration/domain/RiskEvaluation.java`
- `src/main/java/com/fintech/integration/domain/UserManagement.java`
- `src/main/java/com/fintech/integration/domain/Notification.java`
- `openapi.yaml`
- `README.md`
- `src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java`
- `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java`
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java`
- `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducer.java`
- `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java`
- `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java`
- `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java`
- `src/main/java/com/fintech/integration/infrastructure/config/GrpcConfig.java`
- `src/main/java/com/fintech/integration/infrastructure/config/AmqpConfig.java`
- `src/main/java/com/fintech/integration/infrastructure/config/GraphQLConfig.java`
- `src/main/proto/risk_evaluation.proto`
- `src/main/proto/user_management.proto`
- `src/main/resources/schema.graphqls`
- `src/test/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcServiceTest.java`
- `src/test/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducerTest.java`
- `src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/fintech/integration`
- `src/main/java/com/fintech/integration/domain`
- `src/main/java/com/fintech/integration/application`
- `src/main/java/com/fintech/integration/infrastructure`
- `src/main/resources`
- `src/test/java/com/fintech/integration`

## Verificacion

```bash
npx --yes @redocly/cli lint openapi.yaml
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **microservicio reactivo con capas estándar**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Integración, Especialidad Desarrollador, Tecnología API, Advanced
- Brecha que el reto ataca: Mostrar cómo RSocket se diferencia de HTTP/1, HTTP/2 y WebSocket. Aplicado el protocolo gRPC facilita la comunicación entre servicios en una arquitectura de microservicios. ¿Cómo se manejan los mensajes y la cola en AMQP? Explicar el rendimiento y flexibilidad al usar GraphQL en comparación con REST?
- Mision: Candidato con nivel Advanced en arquitectura de APIs

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
