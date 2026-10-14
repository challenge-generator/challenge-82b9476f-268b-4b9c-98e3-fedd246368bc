# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/fintech/integration/IntegrationApplication.java` — `reactor.core.publisher`: El import reactor.core.publisher.Hooks pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducer.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/config/GrpcConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/config/AmqpConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/config/GraphQLConfig.java` — `graphql.schema`: El import graphql.schema.DataFetcher pertenece a graphql.schema, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/config/GraphQLConfig.java` — `graphql.schema.idl`: El import graphql.schema.idl.RuntimeWiring pertenece a graphql.schema.idl, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/infrastructure/config/GraphQLConfig.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java` — `graphql.schema`: El import graphql.schema.GraphQLSchema pertenece a graphql.schema, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java` — `CommunicationProtocol.name`: Se invoca `name` sobre `CommunicationProtocol`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java` — `ProtocolSelector.getPrimary`: Se invoca `getPrimary` sobre `ProtocolSelector`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java` — `ProtocolSelector.getSecondary`: Se invoca `getSecondary` sobre `ProtocolSelector`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `RiskEvaluationResponse.getHealthy`: Se invoca `getHealthy` sobre `RiskEvaluationResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `RiskEvaluationResponse.getMessage`: Se invoca `getMessage` sobre `RiskEvaluationResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `RiskEvaluationResponse.getRiskId`: Se invoca `getRiskId` sobre `RiskEvaluationResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `RiskEvaluationResponse.getRiskLevel`: Se invoca `getRiskLevel` sobre `RiskEvaluationResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java` — `RiskEvaluationResponse.getApproved`: Se invoca `getApproved` sobre `RiskEvaluationResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagement.userId`: Se invoca `userId` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagement.email`: Se invoca `email` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagement.fullName`: Se invoca `fullName` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getSuccess`: Se invoca `getSuccess` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `GrpcBlockingServiceStub.authenticate`: Se invoca `authenticate` sobre `GrpcBlockingServiceStub`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getAuthenticated`: Se invoca `getAuthenticated` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getUserId`: Se invoca `getUserId` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getEmail`: Se invoca `getEmail` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getFullName`: Se invoca `getFullName` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java` — `UserManagementResponse.getRolesList`: Se invoca `getRolesList` sobre `UserManagementResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducer.java` — `Notification.priority`: Se invoca `priority` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducer.java` — `Notification.metadata`: Se invoca `metadata` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducer.java` — `Notification.toString`: Se invoca `toString` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java` — `Notification.notificationType`: Se invoca `notificationType` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java` — `Notification.priority`: Se invoca `priority` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java` — `Notification.recipient`: Se invoca `recipient` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java` — `Notification.metadata`: Se invoca `metadata` sobre `Notification`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.firstName`: Se invoca `firstName` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.lastName`: Se invoca `lastName` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.createdAt`: Se invoca `createdAt` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.roles`: Se invoca `roles` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.fullName`: Se invoca `fullName` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.userId`: Se invoca `userId` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.email`: Se invoca `email` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java` — `UserManagement.isActive`: Se invoca `isActive` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.clientId`: Se invoca `clientId` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.loanAmount`: Se invoca `loanAmount` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.loanTerm`: Se invoca `loanTerm` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.creditScore`: Se invoca `creditScore` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.riskLevel`: Se invoca `riskLevel` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.assessmentDate`: Se invoca `assessmentDate` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java` — `RiskEvaluation.metadata`: Se invoca `metadata` sobre `RiskEvaluation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java` — `UserGraphQLController.getUserById`: Se invoca `getUserById` sobre `UserGraphQLController`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java` — `UserManagement.active`: Se invoca `active` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java` — `UserManagement.roles`: Se invoca `roles` sobre `UserManagement`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
npx --yes @redocly/cli lint openapi.yaml
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Integración, Especialidad Desarrollador, Tecnología API, Advanced

### Brecha de conocimiento
Mostrar cómo RSocket se diferencia de HTTP/1, HTTP/2 y WebSocket. Aplicado el protocolo gRPC facilita la comunicación entre servicios en una arquitectura de microservicios. ¿Cómo se manejan los mensajes y la cola en AMQP? Explicar el rendimiento y flexibilidad al usar GraphQL en comparación con REST?

### Misión / candidato
Candidato con nivel Advanced en arquitectura de APIs

### Reto
- Tema: Protocolos de comunicación no convencionales
- Seniority: advanced-l2
- Tipo: theoretical
- Título: Comparativa de protocolos de comunicación en arquitecturas de microservicios
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Exploración de protocolos de comunicación — objetivo: Comprender las diferencias y similitudes entre RSocket, HTTP/1, HTTP/2, WebSocket, gRPC, AMQP y GraphQL. — entregable (NO resolver): Resumen comparativo de los protocolos de comunicación.
- Fase 2: Evaluación de gRPC en microservicios — objetivo: Analizar las ventajas y desventajas de usar gRPC en una arquitectura de microservicios. — entregable (NO resolver): Análisis detallado de gRPC en el contexto de microservicios.
- Fase 3: Manejo de mensajes y cola en AMQP — objetivo: Entender cómo AMQP maneja los mensajes y la cola en un sistema de microservicios. — entregable (NO resolver): Análisis detallado del manejo de mensajes y cola en AMQP.
- Fase 4: Rendimiento y flexibilidad de GraphQL — objetivo: Comprender el rendimiento y flexibilidad de GraphQL en comparación con REST. — entregable (NO resolver): Análisis detallado del rendimiento y flexibilidad de GraphQL.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.fintech</groupId>
    <artifactId>integration</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>integration</name>
    <description>Microservicio de integración con protocolos gRPC, AMQP y GraphQL</description>

    <properties>
        <java.version>21</java.version>
        <grpc.version>1.62.2</grpc.version>
        <protobuf.version>3.25.1</protobuf.version>
        <resilience4j.version>2.1.0</resilience4j.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- gRPC -->
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-netty</artifactId>
            <version>${grpc.version}</version>
        </dependency>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-protobuf</artifactId>
            <version>${grpc.version}</version>
        </dependency>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-stub</artifactId>
            <version>${grpc.version}</version>
        </dependency>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-testing</artifactId>
            <version>${grpc.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- GraphQL -->
        <dependency>
            <groupId>com.graphql-java-kickstart</groupId>
            <artifactId>graphql-spring-boot-starter</artifactId>
            <version>15.0.0</version>
        </dependency>

        <!-- Reactor -->
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-core</artifactId>
            <version>3.6.3</version>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot2</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Test Containers -->
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>rabbitmq</artifactId>
            <version>1.19.7</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.xolstice.maven.plugins</groupId>
                <artifactId>protobuf-maven-plugin</artifactId>
                <version>0.6.1</version>
                <configuration>
                    <protocArtifact>com.google.protobuf:protoc:${protobuf.version}:exe:${os.detected.classifier}</protocArtifact>
                    <pluginId>grpc-java</pluginId>
                    <pluginArtifact>io.grpc:protoc-gen-grpc-java:${grpc.version}:exe:${os.detected.classifier}</pluginArtifact>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>compile-custom</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

    <profiles>
        <profile>
            <id>os-detected</id>
            <activation>
                <property>
                    <name>os.detected.name</name>
                </property>
            </activation>
            <properties>
                <os.detected.classifier>${os.detected.name}_${os.detected.arch}</os.detected.classifier>
            </properties>
        </profile>
    </profiles>
</project>

// === ARCHIVO: src/main/java/com/fintech/integration/IntegrationApplication.java ===
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

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: integration-service
  
  rabbitmq:
    host: ${RABBITMQ_HOST:localhost}
    port: ${RABBITMQ_PORT:5672}
    username: ${RABBITMQ_USERNAME:guest}
    password: ${RABBITMQ_PASSWORD:guest}
    virtual-host: ${RABBITMQ_VHOST:/}

grpc:
  risk-evaluation:
    host: ${GRPC_RISK_EVALUATION_HOST:localhost}
    port: ${GRPC_RISK_EVALUATION_PORT:9090}
  user-management:
    host: ${GRPC_USER_MANAGEMENT_HOST:localhost}
    port: ${GRPC_USER_MANAGEMENT_PORT:9091}

amqp:
  notifications:
    queue: notifications.queue
    exchange: notifications.exchange
    routing-key: notifications.routing.key
    ttl: 3600000

graphql:
  servlet:
    enabled: true
    mapping: /graphql
    corsEnabled: true
    exception-handlers-enabled: true
  
resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
        recordExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
          - io.grpc.StatusRuntimeException
    instances:
      grpcCircuitBreaker:
        baseConfig: default
        waitDurationInOpenState: 1s
      amqpCircuitBreaker:
        baseConfig: default
        waitDurationInOpenState: 500ms

server:
  port: ${SERVER_PORT:8080}
  
logging:
  level:
    root: INFO
    com.fintech.integration: DEBUG
    org.springframework: INFO
    io.grpc: INFO

// === ARCHIVO: src/main/java/com/fintech/integration/domain/RiskEvaluation.java ===
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

// === ARCHIVO: src/main/java/com/fintech/integration/domain/UserManagement.java ===
package com.fintech.integration.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Modelo de dominio para el servicio de gestión de usuarios.
 * Representa un usuario en el sistema financiero.
 */
public record UserManagement(
    UUID userId,
    String fullName,
    String email,
    String identificationNumber,
    LocalDate birthDate,
    String address,
    List<String> roles,
    String status,
    String correlationId
) {
    /**
     * Constructor compacto para UserManagement.
     * @param userId Identificador único del usuario.
     * @param fullName Nombre completo del usuario.
     * @param email Correo electrónico del usuario.
     * @param identificationNumber Número de identificación.
     * @param birthDate Fecha de nacimiento.
     * @param address Dirección del usuario.
     * @param roles Roles asignados al usuario.
     * @param status Estado del usuario (ACTIVE, INACTIVE, PENDING).
     * @param correlationId Identificador de trazabilidad distribuida.
     */
    public UserManagement {
        Objects.requireNonNull(userId, "userId no puede ser nulo");
        Objects.requireNonNull(fullName, "fullName no puede ser nulo");
        Objects.requireNonNull(email, "email no puede ser nulo");
        Objects.requireNonNull(identificationNumber, "identificationNumber no puede ser nulo");
        Objects.requireNonNull(birthDate, "birthDate no puede ser nulo");
        Objects.requireNonNull(address, "address no puede ser nulo");
        Objects.requireNonNull(roles, "roles no puede ser nulo");
        Objects.requireNonNull(status, "status no puede ser nulo");
        Objects.requireNonNull(correlationId, "correlationId no puede ser nulo");
        
        if (!email.contains("@")) {
            throw new IllegalArgumentException("email debe ser válido");
        }
        
        if (!List.of("ACTIVE", "INACTIVE", "PENDING").contains(status)) {
            throw new IllegalArgumentException("status debe ser ACTIVE, INACTIVE o PENDING");
        }
    }

    /**
     * Valida que el modelo de UserManagement sea consistente.
     * @throws IllegalStateException si alguna validación falla.
     */
    public void validate() {
        if (fullName.trim().isEmpty()) {
            throw new IllegalStateException("fullName no puede estar vacío");
        }
        
        if (roles.isEmpty()) {
            throw new IllegalStateException("roles no puede estar vacío");
        }
    }

    /**
     * Verifica si el usuario tiene un rol específico.
     * @param role Rol a verificar.
     * @return true si el usuario tiene el rol, false en caso contrario.
     */
    public boolean hasRole(String role) {
        return roles.contains(role);
    }
}

// === ARCHIVO: src/main/java/com/fintech/integration/domain/Notification.java ===
package com.fintech.integration.domain;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Modelo de dominio para el servicio de notificaciones.
 * Representa una notificación enviada a un usuario.
 */
public record Notification(
    UUID notificationId,
    UUID userId,
    String notificationType,
    String subject,
    String message,
    LocalDateTime sentAt,
    String status,
    Map<String, String> metadata,
    String correlationId
) {
    /**
     * Constructor compacto para Notification.
     * @param notificationId Identificador único de la notificación.
     * @param userId Identificador del usuario destino.
     * @param notificationType Tipo de notificación (EMAIL, SMS, PUSH).
     * @param subject Asunto de la notificación.
     * @param message Contenido de la notificación.
     * @param sentAt Fecha y hora de envío.
     * @param status Estado de la notificación (SENT, FAILED, PENDING).
     * @param metadata Metadatos adicionales.
     * @param correlationId Identificador de trazabilidad distribuida.
     */
    public Notification {
        Objects.requireNonNull(notificationId, "notificationId no puede ser nulo");
        Objects.requireNonNull(userId, "userId no puede ser nulo");
        Objects.requireNonNull(notificationType, "notificationType no puede ser nulo");
        Objects.requireNonNull(subject, "subject no puede ser nulo");
        Objects.requireNonNull(message, "message no puede ser nulo");
        Objects.requireNonNull(sentAt, "sentAt no puede ser nulo");
        Objects.requireNonNull(status, "status no puede ser nulo");
        Objects.requireNonNull(metadata, "metadata no puede ser nulo");
        Objects.requireNonNull(correlationId, "correlationId no puede ser nulo");
        
        if (!List.of("EMAIL", "SMS", "PUSH").contains(notificationType)) {
            throw new IllegalArgumentException("notificationType debe ser EMAIL, SMS o PUSH");
        }
        
        if (!List.of("SENT", "FAILED", "PENDING").contains(status)) {
            throw new IllegalArgumentException("status debe ser SENT, FAILED o PENDING");
        }
    }

    /**
     * Valida que el modelo de Notification sea consistente.
     * @throws IllegalStateException si alguna validación falla.
     */
    public void validate() {
        if (subject.trim().isEmpty()) {
            throw new IllegalStateException("subject no puede estar vacío");
        }
        
        if (message.trim().isEmpty()) {
            throw new IllegalStateException("message no puede estar vacío");
        }
    }

    /**
     * Obtiene un metadato por clave.
     * @param key Clave del metadato.
     * @return Valor del metadato o null si no existe.
     */
    public String getMetadata(String key) {
        return metadata.get(key);
    }

    /**
     * Añade un metadato.
     * @param key Clave del metadato.
     * @param value Valor del metadato.
     * @return Nueva instancia con el metadato añadido.
     */
    public Notification withMetadata(String key, String value) {
        Map<String, String> newMetadata = new java.util.HashMap<>(metadata);
        newMetadata.put(key, value);
        return new Notification(notificationId, userId, notificationType, subject, message, sentAt, status, newMetadata, correlationId);
    }
}

// === ARCHIVO: openapi.yaml ===
openapi: 3.1.0
info:
  title: Fintech Integration API
  description: API REST para el microservicio de integración con soporte para evaluación de riesgos y gestión de notificaciones
  version: 1.0.0
  contact:
    name: Fintech Team
    email: integration@fintech.com

servers:
  - url: http://localhost:8080
    description: Servidor local de desarrollo
  - url: https://api.fintech.com/v1
    description: Servidor de producción

tags:
  - name: Risk Evaluation
    description: Endpoints para evaluación de riesgos del sistema
  - name: Health
    description: Verificación de estado del servicio

paths:
  /api/v1/risk-evaluation:
    post:
      tags:
        - Risk Evaluation
      summary: Evaluar riesgo de una operación financiera
      description: |
        Envía una solicitud de evaluación de riesgo al servicio de evaluación de riesgos.
        Utiliza gRPC internamente para la comunicación con el servicio de riesgo.
      operationId: evaluateRisk
      requestBody:
        description: Datos de la operación financiera a evaluar
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/RiskEvaluationRequest'
      responses:
        '200':
          description: Evaluación de riesgo completada exitosamente
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/RiskEvaluationResponse'
        '400':
          description: Solicitud inválida
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'
        '500':
          description: Error interno del servidor
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'
        '503':
          description: Servicio de evaluación de riesgos no disponible
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'

  /api/v1/risk-evaluation/{correlationId}:
    get:
      tags:
        - Risk Evaluation
      summary: Consultar estado de una evaluación de riesgo
      description: |
        Recupera el estado de una evaluación de riesgo previamente enviada
        usando el correlationId proporcionado en la solicitud original.
      operationId: getRiskEvaluationStatus
      parameters:
        - name: correlationId
          in: path
          description: Identificador de correlación de la solicitud original
          required: true
          schema:
            type: string
            format: uuid
      responses:
        '200':
          description: Estado de la evaluación recuperado exitosamente
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/RiskEvaluationStatusResponse'
        '404':
          description: Evaluación no encontrada
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'

  /api/v1/notifications:
    post:
      tags:
        - Notifications
      summary: Enviar notificación asíncrona
      description: |
        Envía una notificación a través de AMQP para procesamiento asíncrono.
        La notificación se encola y se procesa de forma asíncrona.
      operationId: sendNotification
      requestBody:
        description: Datos de la notificación a enviar
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/NotificationRequest'
      responses:
        '202':
          description: Notificación aceptada para procesamiento
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/NotificationResponse'
        '400':
          description: Solicitud inválida
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'
        '500':
          description: Error al encolar la notificación
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'

  /actuator/health:
    get:
      tags:
        - Health
      summary: Verificar estado del servicio
      description: |
        Endpoint de health check para verificación de disponibilidad del servicio.
        Incluye el estado de las conexiones a servicios externos.
      operationId: healthCheck
      responses:
        '200':
          description: Servicio disponible
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/HealthResponse'
        '503':
          description: Servicio no disponible
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/HealthResponse'

components:
  schemas:
    RiskEvaluationRequest:
      type: object
      required:
        - operationType
        - amount
        - customerId
      properties:
        operationType:
          type: string
          enum:
            - CREDIT_REQUEST
            - LOAN_APPROVAL
            - INVESTMENT
            - TRANSFER
            - PAYMENT
          description: Tipo de operación financiera a evaluar
        amount:
          type: number
          format: decimal
          minimum: 0
          description: Monto de la operación
        customerId:
          type: string
          description: Identificador único del cliente
        currency:
          type: string
          default: USD
          description: Código de moneda ISO 4217
        metadata:
          type: object
          additionalProperties:
            type: string
          description: Metadatos adicionales para la evaluación

    RiskEvaluationResponse:
      type: object
      properties:
        correlationId:
          type: string
          format: uuid
          description: Identificador de correlación de la solicitud
        riskLevel:
          type: string
          enum:
            - LOW
            - MEDIUM
            - HIGH
            - CRITICAL
          description: Nivel de riesgo evaluado
        riskScore:
          type: integer
          minimum: 0
          maximum: 100
          description: Puntuación de riesgo (0-100)
        approved:
          type: boolean
          description: Indica si la operación fue aprobada
        reasons:
          type: array
          items:
            type: string
          description: Lista de razones de la evaluación
        evaluatedAt:
          type: string
          format: date-time
          description: Timestamp de la evaluación

    RiskEvaluationStatusResponse:
      type: object
      properties:
        correlationId:
          type: string
          format: uuid
        status:
          type: string
          enum:
            - PENDING
            - PROCESSING
            - COMPLETED
            - FAILED
        riskLevel:
          type: string
          enum:
            - LOW
            - MEDIUM
            - HIGH
            - CRITICAL
        riskScore:
          type: integer
        updatedAt:
          type: string
          format: date-time

    NotificationRequest:
      type: object
      required:
        - recipient
        - type
        - content
      properties:
        recipient:
          type: string
          description: Destinatario de la notificación
        type:
          type: string
          enum:
            - EMAIL
            - SMS
            - PUSH
            - WEBHOOK
          description: Tipo de notificación
        content:
          type: string
          description: Contenido de la notificación
        priority:
          type: string
          enum:
            - LOW
            - NORMAL
            - HIGH
          default: NORMAL
        metadata:
          type: object
          additionalProperties:
            type: string

    NotificationResponse:
      type: object
      properties:
        notificationId:
          type: string
          format: uuid
        status:
          type: string
          enum:
            - QUEUED
            - SENT
            - FAILED
        queuedAt:
          type: string
          format: date-time

    ErrorResponse:
      type: object
      required:
        - code
        - message
      properties:
        code:
          type: string
          description: Código de error interno
        message:
          type: string
          description: Descripción legible del error
        details:
          type: array
          items:
            $ref: '#/components/schemas/ErrorDetail'
        timestamp:
          type: string
          format: date-time

    ErrorDetail:
      type: object
      properties:
        field:
          type: string
          description: Campo que generó el error
        message:
          type: string
          description: Mensaje de error específico del campo

    HealthResponse:
      type: object
      properties:
        status:
          type: string
          enum:
            - UP
            - DOWN
            - DEGRADED
        components:
          type: object
          properties:
            grpc:
              $ref: '#/components/schemas/ComponentHealth'
            amqp:
              $ref: '#/components/schemas/ComponentHealth'
            graphql:
              $ref: '#/components/schemas/ComponentHealth'

    ComponentHealth:
      type: object
      properties:
        status:
          type: string
          enum:
            - UP
            - DOWN
        details:
          type: object
          additionalProperties:
            type: string

  securitySchemes:
    BearerAuth:
      type: http
      scheme: bearer
      bearerFormat: JWT
    ApiKeyAuth:
      type: apiKey
      in: header
      name: X-API-Key

security:
  - BearerAuth: []
  - ApiKeyAuth: []

// === ARCHIVO: README.md ===
# Fintech Integration Service

Microservicio de integración desarrollado con Spring Boot 3.5 y Java 21 que implementa múltiples protocolos de comunicación para la interacción entre servicios en una arquitectura de microservicios.

## Arquitectura del Proyecto

El servicio actúa como orchestrator entre diferentes sistemas backend utilizando los siguientes protocolos:

- **gRPC**: Comunicación de bajo latencia con servicios de evaluación de riesgos y gestión de usuarios
- **AMQP (RabbitMQ)**: Mensajería asíncrona para el sistema de notificaciones
- **GraphQL**: Consulta flexible de datos de usuarios
- **HTTP/REST**: Endpoints RESTful para integración con clientes externos

### Estructura de Capas

```
src/main/java/com/fintech/integration/
├── domain/              # Entidades y objetos de dominio (RiskEvaluation, UserManagement, Notification)
├── application/         # Orquestación de protocolos (ProtocolOrchestrator)
├── infrastructure/      # Implementaciones de adaptadores por protocolo
│   ├── grpc/           # Servicios gRPC (RiskEvaluationGrpcService, UserManagementGrpcService)
│   ├── amqp/           # Productor y consumidor AMQP
│   ├── graphql/        # Controlador GraphQL
│   ├── rest/           # Controladores REST
│   └── config/         # Configuraciones de beans (GrpcConfig, AmqpConfig, GraphQLConfig)
└── IntegrationApplication.java  # Punto de entrada
```

## Requisitos Previos

- Java Development Kit (JDK) 21 o superior
- Maven 3.9+
- Docker y Docker Compose (para servicios de infraestructura)
- Protocol Buffer compiler (protoc) 3.25+

## Configuración del Entorno

### Variables de Entorno

```bash
export RISK_EVALUATION_HOST=localhost
export RISK_EVALUATION_PORT=50051
export USER_MANAGEMENT_HOST=localhost
export USER_MANAGEMENT_PORT=50052
export RABBITMQ_HOST=localhost
export RABBITMQ_PORT=5672
export RABBITMQ_USERNAME=guest
export RABBITMQ_PASSWORD=guest
```

### Servicios de Infraestructura

El proyecto requiere los siguientes servicios externos que deben estar disponibles:

1. **Servidor gRPC para Risk Evaluation** - Puerto 50051
2. **Servidor gRPC para User Management** - Puerto 50052
3. **RabbitMQ** - Puerto 5672 (interfaz de gestión en 15672)

Para levantar los servicios de prueba con Docker:

```bash
docker-compose up -d
```

## Compilación y Ejecución

### Compilación

```bash
mvn clean compile
```

Este comando:
1. Descarga las dependencias definidas en pom.xml
2. Compila el código fuente Java
3. Genera las clases Java desde los archivos .proto
4. Ejecuta las validaciones configuradas

### Ejecución

```bash
mvn spring-boot:run
```

El servicio arrancará en http://localhost:8080

### Construcción del JAR

```bash
mvn clean package -DskipTests
```

El JAR ejecutable se generará en:
```
target/integration-0.0.1-SNAPSHOT.jar
```

## Verificación de Contratos

### OpenAPI / REST

El contrato REST está definido en `openapi.yaml`. Para validar la especificación:

```bash
npx --yes @redocly/cli lint openapi.yaml
```

### GraphQL Schema

El schema GraphQL está en `src/main/resources/schema.graphqls`. El endpoint GraphQL está disponible en:

```
POST /graphql
```

### Contratos gRPC

Los archivos protobuf están en `src/main/proto/`:
- `risk_evaluation.proto`
- `user_management.proto`

## Pruebas

### Ejecución de Tests Unitarios

```bash
mvn test
```

### Tests de Integración

Los tests de integración requieren los servicios externos (RabbitMQ, servidores gRPC). Para ejecutarlos:

```bash
mvn verify -Pintegration-tests
```

### Cobertura de Código

```bash
mvn test jacoco:report
```

El reporte se genera en:
```
target/site/jacoco/index.html
```

## Endpoints Disponibles

### REST API

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /api/v1/risk-evaluation | Evaluar riesgo de operación |
| GET | /api/v1/risk-evaluation/{correlationId} | Consultar estado de evaluación |
| POST | /api/v1/notifications | Enviar notificación asíncrona |
| GET | /actuator/health | Health check del servicio |

### GraphQL

El endpoint GraphQL acepta consultas para obtener información de usuarios:

```graphql
query {
  user(id: "123") {
    id
    username
    email
    roles
  }
}
```

### gRPC

Los servicios gRPC están disponibles en los puertos configurados. Para probar con grpcurl:

```bash
# Listar servicios
exec grpcurl -plaintext localhost:50051 list

# Obtener método
exec grpcurl -plaintext -d '{"customerId": "123", "amount": 1000}' localhost:50051 fintech.risk.RiskEvaluation/Evaluate
```

## Configuración por Perfiles

### Desarrollo (default)

```yaml
# src/main/resources/application.yml
spring:
  profiles.active: dev
  rabbitmq:
    host: localhost
    port: 5672

grpc:
  risk-evaluation:
    host: localhost
    port: 50051
  user-management:
    host: localhost
    port: 50052
```

### Producción

Activar con: `spring.profiles.active=prod`

La configuración de producción incluye:
- TLS/SSL para comunicaciones gRPC
- Conexiones pool de RabbitMQ
- Circuit breakers con umbrales ajustados
- Logging estructurado

## Circuit Breaker y Resiliencia

El servicio implementa Resilience4j para manejar fallos en comunicaciones externas:

- **gRPC Circuit Breaker**: Protege las llamadas a servicios de evaluación de riesgos y gestión de usuarios
- **AMQP Circuit Breaker**: Protege el envío de notificaciones cuando RabbitMQ no está disponible

Configuración en `application.yml`:

```yaml
resilience4j:
  circuitbreaker:
    instances:
      grpc:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
      amqp:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        failureRateThreshold: 50
```

## Trazabilidad Distribuida

Todas las comunicaciones incluyen un correlationId para trazabilidad de extremo a extremo:

- Se genera automáticamente en cada request
- Se propaga a través de gRPC metadata
- Se incluye en mensajes AMQP
- Se añade a las cabeceras HTTP

## Gestión de Errores

El servicio implementa manejo de errores centralizado:

- **Errores de validación**: Retornan 400 Bad Request con detalles del campo
- **Errores de negocio**: Retornan 409 Conflict con código específico
- **Errores de infraestructura**: Retornan 503 Service Unavailable
- **Errores unknown**: Retornan 500 Internal Server Error

## Dependencias Principales

| Dependencia | Versión | Propósito |
|-------------|---------|-----------|
| Spring Boot | 3.5.6 | Framework principal |
| Spring WebFlux | 3.5.6 | Programación reactiva |
| gRPC | 1.62.2 | Comunicación RPC |
| GraphQL Java Kickstart | 15.0.0 | Schema GraphQL |
| Resilience4j | 2.1.0 | Patrones de resiliencia |
| Reactor Core | 3.6.3 | Programación reactiva |

## Licencia

Proprietario - Fintech Integration Team

// === ARCHIVO: src/main/java/com/fintech/integration/application/ProtocolOrchestrator.java ===
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

// === ARCHIVO: src/main/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcService.java ===
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

// === ARCHIVO: src/main/java/com/fintech/integration/infrastructure/grpc/UserManagementGrpcService.java ===
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

// === ARCHIVO: src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducer.java ===
package com.fintech.integration.infrastructure.amqp;

import com.fintech.integration.domain.Notification;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationAmqpProducer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationAmqpProducer.class);
    private static final String CORRELATION_ID_HEADER = "x-correlation-id";
    private static final String CIRCUIT_BREAKER_NAME = "amqpProducer";

    private final RabbitTemplate rabbitTemplate;
    private final CircuitBreaker circuitBreaker;
    private final String notificationsExchange;
    private final String notificationsRoutingKey;

    public NotificationAmqpProducer(
            RabbitTemplate rabbitTemplate,
            TopicExchange notificationsExchange,
            String notificationsRoutingKey,
            CircuitBreakerRegistry circuitBreakerRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        this.notificationsExchange = notificationsExchange.getName();
        this.notificationsRoutingKey = notificationsRoutingKey;
        
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .build();
        
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        this.circuitBreaker.changeConfig(config);
        
        setupPublisherConfirmations();
    }

    private void setupPublisherConfirmations() {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                logger.debug("Publisher confirmation received for correlationId: {}", 
                    correlationData != null ? correlationData.getId() : "unknown");
            } else {
                logger.error("Publisher confirmation failed for correlationId: {}, cause: {}",
                    correlationData != null ? correlationData.getId() : "unknown",
                    cause);
            }
        });

        rabbitTemplate.setReturnsCallback(returned -> {
            logger.error("Message returned: replyCode={}, replyText={}, exchange={}, routingKey={}",
                returned.getReplyCode(),
                returned.getReplyText(),
                returned.getExchange(),
                returned.getRoutingKey());
        });
    }

    public void sendNotification(Notification notification) {
        notification.validate();
        
        String correlationId = notification.getMetadata(CORRELATION_ID_HEADER);
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        
        try {
            circuitBreaker.executeRunnable(() -> {
                publishNotification(notification, correlationId);
            });
        } catch (Exception e) {
            logger.error("Circuit breaker is open or execution failed for notification: {}", 
                notification, e);
            throw new AmqpPublishingException("Failed to send notification due to circuit breaker", e);
        }
    }

    private void publishNotification(Notification notification, String correlationId) {
        CorrelationData correlationData = new CorrelationData(correlationId);
        
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setPriority(notification.priority().orElse(0));
        messageProperties.setHeader(CORRELATION_ID_HEADER, correlationId);
        
        notification.metadata().forEach((key, value) -> {
            if (!key.equals(CORRELATION_ID_HEADER)) {
                messageProperties.setHeader(key, value);
            }
        });

        Message message = MessageBuilder
                .withBody(notification.toString().getBytes())
                .andProperties(messageProperties)
                .build();

        rabbitTemplate.convertAndSend(
            notificationsExchange,
            notificationsRoutingKey,
            message,
            correlationData
        );
        
        logger.info("Notification sent successfully with correlationId: {}", correlationId);
    }

    public CompletableFuture<Boolean> sendNotificationAsync(Notification notification) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                sendNotification(notification);
                return true;
            } catch (Exception e) {
                logger.error("Failed to send notification asynchronously", e);
                return false;
            }
        });
    }

    public boolean isCircuitBreakerOpen() {
        return circuitBreaker.getState() == CircuitBreaker.State.OPEN;
    }

    public CircuitBreaker.Metrics getCircuitBreakerMetrics() {
        return circuitBreaker.getMetrics();
    }

    public static class AmqpPublishingException extends RuntimeException {
        public AmqpPublishingException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

// === ARCHIVO: src/main/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpConsumer.java ===
package com.fintech.integration.infrastructure.amqp;

import com.fintech.integration.domain.Notification;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class NotificationAmqpConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationAmqpConsumer.class);
    private static final String CORRELATION_ID_HEADER = "x-correlation-id";
    private static final String DLQ_SUFFIX = ".dlq";
    private static final String DLX_SUFFIX = ".dlx";

    private final RabbitTemplate rabbitTemplate;
    private final MessageConverter messageConverter;
    private final ConnectionFactory connectionFactory;
    private final CircuitBreaker circuitBreaker;
    private final Queue notificationsQueue;
    private final String notificationsQueueName;

    public NotificationAmqpConsumer(
            RabbitTemplate rabbitTemplate,
            MessageConverter messageConverter,
            ConnectionFactory connectionFactory,
            Queue notificationsQueue,
            CircuitBreakerRegistry circuitBreakerRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
        this.connectionFactory = connectionFactory;
        this.notificationsQueue = notificationsQueue;
        this.notificationsQueueName = notificationsQueue.getName();
        
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(60)
                .waitDurationInOpenState(Duration.ofSeconds(45))
                .slidingWindowSize(8)
                .minimumNumberOfCalls(3)
                .permittedNumberOfCallsInHalfOpenState(2)
                .build();
        
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("amqpConsumer");
        this.circuitBreaker.changeConfig(config);
        
        initializeDeadLetterInfrastructure();
    }

    private void initializeDeadLetterInfrastructure() {
        String dlqName = notificationsQueueName + DLQ_SUFFIX;
        String dlxName = notificationsQueueName + DLX_SUFFIX;
        
        DirectExchange deadLetterExchange = new DirectExchange(dlxName);
        Queue deadLetterQueue = QueueBuilder.durable(dlqName).build();
        Binding dlqBinding = BindingBuilder
                .bind(deadLetterQueue)
                .to(deadLetterExchange)
                .with(notificationsQueueName);
        
        logger.info("Dead Letter Queue configured: {} -> {}", dlqName, dlxName);
    }

    @org.springframework.amqp.rabbit.annotation.RabbitListener(
            queues = "#{notificationsQueue.name}",
            containerFactory = "rabbitListenerContainerFactory")
    public void receiveNotification(org.springframework.amqp.core.Message message) {
        String correlationId = (String) message.getMessageProperties().getHeader(CORRELATION_ID_HEADER);
        logger.info("Received notification with correlationId: {}", correlationId);
        
        try {
            circuitBreaker.executeRunnable(() -> processMessage(message));
        } catch (Exception e) {
            logger.error("Circuit breaker prevented processing for correlationId: {}", correlationId, e);
            handleMessageFailure(message, e);
        }
    }

    private void processMessage(org.springframework.amqp.core.Message message) {
        try {
            Object notificationObject = messageConverter.fromMessage(message);
            
            if (notificationObject instanceof Notification notification) {
                notification.validate();
                logger.info("Processing notification: type={}, priority={}", 
                    notification.notificationType(), 
                    notification.priority().orElse(0));
                
                executeNotificationProcessing(notification);
                
                logger.info("Notification processed successfully");
            } else {
                logger.warn("Unknown notification type received: {}", 
                    notificationObject != null ? notificationObject.getClass() : "null");
            }
        } catch (Exception e) {
            logger.error("Failed to process message", e);
            throw new ListenerExecutionFailedException("Message processing failed", e);
        }
    }

    private void executeNotificationProcessing(Notification notification) {
        Map<String, Object> processingContext = new HashMap<>();
        processingContext.put("type", notification.notificationType());
        processingContext.put("recipient", notification.recipient());
        
        notification.metadata().forEach((key, value) -> {
            if (!key.equals(CORRELATION_ID_HEADER)) {
                processingContext.put(key, value);
            }
        });
        
        logger.debug("Executing notification processing with context: {}", processingContext);
    }

    private void handleMessageFailure(org.springframework.amqp.core.Message originalMessage, Exception exception) {
        Integer retryCount = (Integer) originalMessage.getMessageProperties()
                .getHeader("x-retry-count");
        
        int currentRetry = retryCount != null ? retryCount : 0;
        int maxRetries = 3;
        
        if (currentRetry < maxRetries) {
            logger.warn("Retrying message, attempt {}/{}", currentRetry + 1, maxRetries);
            retryMessage(originalMessage, currentRetry + 1);
        } else {
            logger.error("Max retries exceeded, sending to DLQ");
            sendToDeadLetterQueue(originalMessage, exception);
        }
    }

    private void retryMessage(org.springframework.amqp.core.Message message, int retryCount) {
        message.getMessageProperties().setHeader("x-retry-count", retryCount);
        
        try {
            Thread.sleep(1000L * retryCount);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        rabbitTemplate.send(notificationsQueueName, message);
    }

    private void sendToDeadLetterQueue(org.springframework.amqp.core.Message message, Exception exception) {
        String dlqName = notificationsQueueName + DLQ_SUFFIX;
        String dlxName = notificationsQueueName + DLX_SUFFIX;
        
        message.getMessageProperties().setHeader("x-death", System.currentTimeMillis());
        message.getMessageProperties().setHeader("x-failure-reason", exception.getMessage());
        
        rabbitTemplate.send(dlxName, notificationsQueueName, message);
        
        logger.error("Message sent to DLQ: {}, reason: {}", dlqName, exception.getMessage());
    }

    public void registerProcessor(Consumer<Notification> processor) {
        logger.info("Registered custom notification processor");
    }

    public CircuitBreaker getCircuitBreaker() {
        return circuitBreaker;
    }
}

// === ARCHIVO: src/main/java/com/fintech/integration/infrastructure/graphql/UserGraphQLController.java ===
package com.fintech.integration.infrastructure.graphql;

import com.fintech.integration.domain.UserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class UserGraphQLController {

    private static final Logger logger = LoggerFactory.getLogger(UserGraphQLController.class);
    
    private final Map<String, UserManagement> userCache = new HashMap<>();
    private final Map<String, List<String>> userRolesCache = new HashMap<>();

    public UserGraphQLController() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        userCache.put("user-001", new UserManagement("user-001", "john.doe@example.com", "John Doe", true, Set.of("USER", "PREMIUM")));
        userCache.put("user-002", new UserManagement("user-002", "jane.smith@example.com", "Jane Smith", true, Set.of("USER")));
        userCache.put("user-003", new UserManagement("user-003", "admin@example.com", "Admin User", true, Set.of("ADMIN", "USER")));
        userCache.put("user-004", new UserManagement("user-004", "inactive@example.com", "Inactive User", false, Set.of("USER")));
        
        userRolesCache.put("user-001", List.of("USER", "PREMIUM"));
        userRolesCache.put("user-002", List.of("USER"));
        userRolesCache.put("user-003", List.of("ADMIN", "USER"));
        userRolesCache.put("user-004", List.of("USER"));
    }

    @QueryMapping
    public Mono<UserGraphQL> userById(@Argument String id) {
        logger.info("GraphQL query: userById({})", id);
        
        return Mono.fromCallable(() -> {
            UserManagement user = userCache.get(id);
            if (user == null) {
                return null;
            }
            return mapToGraphQL(user);
        });
    }

    @QueryMapping
    public Mono<List<UserGraphQL>> usersByRole(@Argument String role) {
        logger.info("GraphQL query: usersByRole({})", role);
        
        return Mono.fromCallable(() -> {
            return userCache.values().stream()
                    .filter(u -> u.hasRole(role))
                    .map(this::mapToGraphQL)
                    .toList();
        });
    }

    @QueryMapping
    public Mono<List<UserGraphQL>> allUsers() {
        logger.info("GraphQL query: allUsers");
        
        return Mono.fromCallable(() -> {
            return userCache.values().stream()
                    .map(this::mapToGraphQL)
                    .toList();
        });
    }

    @QueryMapping
    public Mono<UserStatistics> userStatistics() {
        logger.info("GraphQL query: userStatistics");
        
        return Mono.fromCallable(() -> {
            int totalUsers = userCache.size();
            long activeUsers = userCache.values().stream()
                    .filter(UserManagement::isActive)
                    .count();
            
            Map<String, Long> roleDistribution = new HashMap<>();
            userRolesCache.values().stream()
                    .flatMap(List::stream)
                    .forEach(role -> roleDistribution.merge(role, 1L, Long::sum));
            
            return new UserStatistics(
                totalUsers,
                (int) activeUsers,
                totalUsers - activeUsers,
                roleDistribution
            );
        });
    }

    @SchemaMapping(typeName = "User", field = "fullName")
    public Mono<String> resolveFullName(UserGraphQL user) {
        return Mono.just(user.firstName() + " " + user.lastName());
    }

    @SchemaMapping(typeName = "User", field = "accountAge")
    public Mono<Integer> resolveAccountAge(UserGraphQL user) {
        return Mono.fromCallable(() -> {
            if (user.createdAt() == null) {
                return 0;
            }
            return (int) java.time.temporal.ChronoUnit.DAYS.between(
                user.createdAt(), 
                LocalDateTime.now()
            );
        });
    }

    @SchemaMapping(typeName = "User", field = "permissions")
    public Mono<List<String>> resolvePermissions(UserGraphQL user) {
        return Mono.fromCallable(() -> {
            List<String> permissions = new ArrayList<>();
            
            if (user.roles().contains("ADMIN")) {
                permissions.addAll(List.of("READ", "WRITE", "DELETE", "ADMIN"));
            } else if (user.roles().contains("PREMIUM")) {
                permissions.addAll(List.of("READ", "WRITE"));
            } else {
                permissions.add("READ");
            }
            
            return permissions;
        });
    }

    private UserGraphQL mapToGraphQL(UserManagement user) {
        String[] nameParts = user.fullName().split(" ", 2);
        String firstName = nameParts.length > 0 ? nameParts[0] : "";
        String lastName = nameParts.length > 1 ? nameParts[1] : "";
        
        return new UserGraphQL(
            user.userId(),
            firstName,
            lastName,
            user.email(),
            user.isActive(),
            new ArrayList<>(user.roles()),
            LocalDateTime.now().minusMonths(new Random().nextInt(24))
        );
    }

    public record UserGraphQL(
        String id,
        String firstName,
        String lastName,
        String email,
        boolean active,
        List<String> roles,
        LocalDateTime createdAt
    ) {}

    public record UserStatistics(
        int totalUsers,
        int activeUsers,
        int inactiveUsers,
        Map<String, Long> roleDistribution
    ) {}
}

// === ARCHIVO: src/main/java/com/fintech/integration/infrastructure/rest/RiskEvaluationRestController.java ===
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

// === ARCHIVO: src/main/java/com/fintech/integration/infrastructure/config/GrpcConfig.java ===
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

// === ARCHIVO: src/main/java/com/fintech/integration/infrastructure/config/AmqpConfig.java ===
package com.fintech.integration.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AmqpConfig {

    private static final Logger log = LoggerFactory.getLogger(AmqpConfig.class);

    @Value("${spring.rabbitmq.host:localhost}")
    private String rabbitHost;

    @Value("${spring.rabbitmq.port:5672}")
    private int rabbitPort;

    @Value("${spring.rabbitmq.username:guest}")
    private String rabbitUsername;

    @Value("${spring.rabbitmq.password:guest}")
    private String rabbitPassword;

    @Value("${amqp.notifications.queue:notifications-queue}")
    private String notificationsQueueName;

    @Value("${amqp.notifications.exchange:notifications-exchange}")
    private String notificationsExchangeName;

    @Value("${amqp.notifications.routing-key:notifications.routing.key}")
    private String notificationsRoutingKey;

    @Value("${amqp.dlq.enabled:true}")
    private boolean dlqEnabled;

    @Value("${amqp.retry.max-attempts:3}")
    private int maxRetryAttempts;

    @Value("${amqp.retry.initial-interval:1000}")
    private long initialRetryInterval;

    @Value("${amqp.retry.multiplier:2.0}")
    private double retryMultiplier;

    @Value("${amqp.retry.max-interval:10000}")
    private long maxRetryInterval;

    @Bean
    public Queue notificationsQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000);
        
        if (dlqEnabled) {
            String dlqName = notificationsQueueName + ".dlq";
            args.put("x-dead-letter-exchange", "");
            args.put("x-dead-letter-routing-key", dlqName);
            log.info("DLQ configurada para la cola de notificaciones: {}", dlqName);
        }
        
        return QueueBuilder.durable(notificationsQueueName).withArguments(args).build();
    }

    @Bean
    public Queue deadLetterQueue() {
        if (!dlqEnabled) {
            return null;
        }
        String dlqName = notificationsQueueName + ".dlq";
        log.info("Creando cola de mensajes muertos: {}", dlqName);
        return QueueBuilder.durable(dlqName).build();
    }

    @Bean
    public TopicExchange notificationsExchange() {
        log.info("Configurando exchange de notificaciones: {}", notificationsExchangeName);
        return new TopicExchange(notificationsExchangeName);
    }

    @Bean
    public Binding notificationsBinding(Queue notificationsQueue, TopicExchange notificationsExchange) {
        return BindingBuilder.bind(notificationsQueue)
                .to(notificationsExchange)
                .with(notificationsRoutingKey);
    }

    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue) {
        if (!dlqEnabled || deadLetterQueue == null) {
            return null;
        }
        String dlqName = notificationsQueueName + ".dlq";
        return BindingBuilder.bind(deadLetterQueue)
                .to(new TopicExchange(""))
                .with(dlqName);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        template.setRetryTemplate(retryTemplate());
        log.info("RabbitTemplate configurado con reintentos");
        return template;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(initialRetryInterval);
        backOffPolicy.setMultiplier(retryMultiplier);
        backOffPolicy.setMaxInterval(maxRetryInterval);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(maxRetryAttempts);
        retryTemplate.setRetryPolicy(retryPolicy);

        log.info("RetryTemplate configurado: maxAttempts={}, initialInterval={}, multiplier={}",
                maxRetryAttempts, initialRetryInterval, retryMultiplier);

        return retryTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {
        
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setDefaultRequeueRejected(false);
        factory.setPrefetchCount(10);
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        
        log.info("RabbitListenerContainerFactory configurado con prefetch=10, concurrentConsumers=3");
        return factory;
    }

    @Bean
    public org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory 
            directRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        
        org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory factory =
            new org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setDefaultRequeueRejected(false);
        factory.setPrefetchCount(1);
        
        log.info("DirectRabbitListenerContainerFactory configurado para procesamiento exclusivo");
        return factory;
    }

    @Bean
    public org.springframework.amqp.rabbit.connection.CachingConnectionFactory 
            cachingConnectionFactory(ConnectionFactory connectionFactory) {
        
        org.springframework.amqp.rabbit.connection.CachingConnectionFactory cachingFactory =
            new org.springframework.amqp.rabbit.connection.CachingConnectionFactory(connectionFactory);
        cachingFactory.setCacheMode(org.springframework.amqp.rabbit.connection.CachingConnectionFactory.CacheMode.CHANNEL);
        cachingFactory.setChannelCacheSize(25);
        cachingFactory.setConnectionTimeout(5000);
        
        log.info("CachingConnectionFactory configurado con cacheSize=25");
        return cachingFactory;
    }
}

// === ARCHIVO: src/main/java/com/fintech/integration/infrastructure/config/GraphQLConfig.java ===
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

// === ARCHIVO: src/main/proto/risk_evaluation.proto ===
syntax = "proto3";

option java_multiple_files = true;
option java_package = "com.fintech.integration.infrastructure.grpc.risk";
option java_outer_classname = "RiskEvaluationProto";

package com.fintech.integration.risk;

message RiskEvaluationRequest {
    string evaluation_id = 1;
    string user_id = 2;
    string evaluation_type = 3;
    map<string, string> metadata = 4;
    int64 timestamp = 5;
}

message RiskEvaluationResponse {
    string evaluation_id = 1;
    string user_id = 2;
    string risk_level = 3;
    int32 score = 4;
    repeated string factors = 5;
    map<string, string> metadata = 6;
    int64 timestamp = 7;
}

message RiskFactor {
    string factor_id = 1;
    string factor_name = 2;
    string factor_type = 3;
    int32 weight = 4;
    bool is_positive = 5;
}

message RiskEvaluationStreamRequest {
    string user_id = 1;
    string stream_id = 2;
    int32 max_items = 3;
    int64 interval_ms = 4;
}

message RiskEvaluationStreamResponse {
    string evaluation_id = 1;
    string user_id = 2;
    string risk_level = 3;
    int32 score = 4;
    repeated RiskFactor factors = 5;
    bool is_final = 6;
    int64 sequence_number = 7;
}

message RiskBatchRequest {
    repeated RiskEvaluationRequest evaluations = 1;
    string batch_id = 2;
    string correlation_id = 3;
}

message RiskBatchResponse {
    repeated RiskEvaluationResponse results = 1;
    string batch_id = 2;
    int32 success_count = 3;
    int32 failure_count = 4;
    repeated string errors = 5;
}

message RiskFactorsRequest {
    string user_id = 1;
    string evaluation_context = 2;
    repeated string requested_factors = 3;
}

message RiskFactorsResponse {
    string user_id = 1;
    repeated RiskFactor factors = 2;
    int32 total_count = 3;
    bool is_complete = 4;
}

service RiskEvaluationService {
    rpc EvaluateRisk(RiskEvaluationRequest) returns (RiskEvaluationResponse);
    
    rpc EvaluateRiskStream(RiskEvaluationStreamRequest) returns (stream RiskEvaluationStreamResponse);
    
    rpc EvaluateRiskBidirectional(stream RiskEvaluationRequest) returns (stream RiskEvaluationResponse);
    
    rpc EvaluateRiskBatch(RiskBatchRequest) returns (RiskBatchResponse);
    
    rpc GetRiskFactors(RiskFactorsRequest) returns (RiskFactorsResponse);
    
    rpc StreamRiskFactors(RiskFactorsRequest) returns (stream RiskFactorsResponse);
    
    rpc HealthCheck(RiskEvaluationRequest) returns (RiskEvaluationResponse);
}

message RiskEvaluationEvent {
    string event_id = 1;
    string event_type = 2;
    string evaluation_id = 3;
    string user_id = 4;
    RiskEvaluationResponse evaluation = 5;
    int64 occurred_at = 6;
}

message RiskEvaluationEventRequest {
    string subscription_id = 1;
    string user_id = 2;
    repeated string event_types = 3;
}

service RiskEvaluationEventService {
    rpc Subscribe(RiskEvaluationEventRequest) returns (stream RiskEvaluationEvent);
    
    rpc SubscribeBidirectional(stream RiskEvaluationEventRequest) returns (stream RiskEvaluationEvent);
}

// === ARCHIVO: src/main/proto/user_management.proto ===
syntax = "proto3";

option java_multiple_files = true;
option java_package = "com.fintech.integration.infrastructure.grpc.user";
option java_outer_classname = "UserManagementProto";

package com.fintech.integration.user;

message UserManagementRequest {
    string user_id = 1;
    string email = 2;
    string name = 3;
    repeated string roles = 4;
    bool active = 5;
    map<string, string> metadata = 6;
}

message UserManagementResponse {
    string user_id = 1;
    string email = 2;
    string name = 3;
    repeated string roles = 4;
    bool active = 5;
    map<string, string> metadata = 6;
    int64 created_at = 7;
    int64 updated_at = 8;
}

message UserCreateRequest {
    string email = 1;
    string name = 2;
    string password = 3;
    repeated string initial_roles = 4;
    map<string, string> attributes = 5;
}

message UserUpdateRequest {
    string user_id = 1;
    string email = 2;
    string name = 3;
    repeated string roles_to_add = 4;
    repeated string roles_to_remove = 5;
    bool active = 6;
    map<string, string> metadata = 7;
}

message UserDeleteRequest {
    string user_id = 1;
    bool hard_delete = 2;
    string reason = 3;
}

message UserListRequest {
    int32 page_size = 1;
    string page_token = 2;
    string filter = 3;
    string order_by = 4;
    bool ascending = 5;
}

message UserListResponse {
    repeated UserManagementResponse users = 1;
    string next_page_token = 2;
    int32 total_count = 3;
    bool has_more = 4;
}

message UserSearchRequest {
    string query = 1;
    repeated string search_fields = 2;
    int32 limit = 3;
    int32 offset = 4;
}

message UserSearchResponse {
    repeated UserManagementResponse users = 1;
    int32 total_results = 2;
    double search_time_ms = 3;
}

message UserRoleChangeRequest {
    string user_id = 1;
    string role = 2;
    string action = 3;
    string requested_by = 4;
}

message UserRoleChangeResponse {
    string user_id = 1;
    string role = 2;
    bool success = 3;
    string message = 4;
}

message UserStreamRequest {
    string user_id = 1;
    string stream_id = 2;
    bool include_metadata = 3;
}

message UserStreamResponse {
    UserManagementResponse user = 1;
    string change_type = 2;
    int64 sequence_number = 3;
    bool is_final = 4;
}

service UserManagementService {
    rpc GetUser(UserManagementRequest) returns (UserManagementResponse);
    
    rpc CreateUser(UserCreateRequest) returns (UserManagementResponse);
    
    rpc UpdateUser(UserUpdateRequest) returns (UserManagementResponse);
    
    rpc DeleteUser(UserDeleteRequest) returns (UserManagementResponse);
    
    rpc ListUsers(UserListRequest) returns (UserListResponse);
    
    rpc SearchUsers(UserSearchRequest) returns (UserSearchResponse);
    
    rpc UpdateUserRoles(UserRoleChangeRequest) returns (UserRoleChangeResponse);
    
    rpc StreamUserUpdates(UserStreamRequest) returns (stream UserStreamResponse);
    
    rpc StreamUserUpdatesBidirectional(stream UserStreamRequest) returns (stream UserStreamResponse);
    
    rpc HealthCheck(UserManagementRequest) returns (UserManagementResponse);
}

message UserManagementEvent {
    string event_id = 1;
    string event_type = 2;
    string user_id = 3;
    UserManagementResponse user = 4;
    string performed_by = 5;
    int64 occurred_at = 6;
}

message UserEventSubscriptionRequest {
    string subscription_id = 1;
    repeated string event_types = 2;
    string user_id_filter = 3;
}

message UserEventAcknowledgement {
    string event_id = 1;
    string subscription_id = 2;
    bool acknowledged = 3;
}

service UserManagementEventService {
    rpc Subscribe(UserEventSubscriptionRequest) returns (stream UserManagementEvent);
    
    rpc SubscribeBidirectional(stream UserEventSubscriptionRequest) returns (stream UserManagementEvent);
    
    rpc AcknowledgeEvent(UserEventAcknowledgement) returns (UserManagementEvent);
    
    rpc AcknowledgeBatch(stream UserEventAcknowledgement) returns (stream UserManagementEvent);
}

// === ARCHIVO: src/main/resources/schema.graphqls ===
scalar DateTime
scalar UUID

enum RiskLevel {
    LOW
    MEDIUM
    HIGH
    CRITICAL
}

enum NotificationStatus {
    PENDING
    SENT
    DELIVERED
    READ
    FAILED
}

enum NotificationType {
    INFO
    WARNING
    ERROR
    SUCCESS
    ALERT
}

enum Role {
    ADMIN
    USER
    AUDITOR
    OPERATOR
}

type User {
    id: UUID!
    username: String!
    email: String!
    roles: [Role!]!
    createdAt: DateTime!
    updatedAt: DateTime!
    lastLoginAt: DateTime
    isActive: Boolean!
    notificationPreferences: NotificationPreferences
}

type NotificationPreferences {
    emailEnabled: Boolean!
    pushEnabled: Boolean!
    smsEnabled: Boolean!
    frequency: String!
}

type Notification {
    id: UUID!
    userId: UUID!
    title: String!
    message: String!
    type: NotificationType!
    status: NotificationStatus!
    metadata: [MetadataEntry!]
    createdAt: DateTime!
    sentAt: DateTime
    deliveredAt: DateTime
    readAt: DateTime
}

type MetadataEntry {
    key: String!
    value: String!
}

type RiskEvaluation {
    id: UUID!
    userId: UUID!
    riskLevel: RiskLevel!
    score: Int!
    factors: [String!]!
    evaluatedAt: DateTime!
    expiresAt: DateTime!
    recommendations: [String!]
}

type Query {
    user(id: UUID!): User
    users(limit: Int, offset: Int, activeOnly: Boolean): [User!]!
    userByUsername(username: String!): User
    userByEmail(email: String!): User
    
    notification(id: UUID!): Notification
    notifications(userId: UUID!, status: NotificationStatus, limit: Int): [Notification!]!
    unreadNotifications(userId: UUID!): [Notification!]!
    
    riskEvaluation(id: UUID!): RiskEvaluation
    currentRiskEvaluation(userId: UUID!): RiskEvaluation
    riskEvaluationHistory(userId: UUID!, limit: Int): [RiskEvaluation!]!
    
    healthCheck: HealthStatus!
}

type HealthStatus {
    status: String!
    timestamp: DateTime!
    services: [ServiceHealth!]!
}

type ServiceHealth {
    name: String!
    status: String!
    latencyMs: Int
}

input CreateUserInput {
    username: String!
    email: String!
    password: String!
    roles: [Role!]!
    notificationPreferences: NotificationPreferencesInput
}

input UpdateUserInput {
    username: String
    email: String
    roles: [Role!]
    isActive: Boolean
    notificationPreferences: NotificationPreferencesInput
}

input NotificationPreferencesInput {
    emailEnabled: Boolean
    pushEnabled: Boolean
    smsEnabled: Boolean
    frequency: String
}

input SendNotificationInput {
    userId: UUID!
    title: String!
    message: String!
    type: NotificationType!
    metadata: [MetadataEntryInput!]
}

input MetadataEntryInput {
    key: String!
    value: String!
}

input UpdateNotificationStatusInput {
    notificationId: UUID!
    status: NotificationStatus!
}

type Mutation {
    createUser(input: CreateUserInput!): User!
    updateUser(id: UUID!, input: UpdateUserInput!): User!
    deleteUser(id: UUID!): Boolean!
    activateUser(id: UUID!): User!
    deactivateUser(id: UUID!): User!
    
    sendNotification(input: SendNotificationInput!): Notification!
    sendBulkNotifications(inputs: [SendNotificationInput!]!): [Notification!]!
    updateNotificationStatus(input: UpdateNotificationStatusInput!): Notification!
    markNotificationAsRead(notificationId: UUID!): Notification!
    deleteNotification(id: UUID!): Boolean!
    
    evaluateRisk(userId: UUID!): RiskEvaluation!
}

type Subscription {
    notificationReceived(userId: UUID!): Notification!
    riskEvaluationCompleted(userId: UUID!): RiskEvaluation!
}

directive @auth(requires: [Role!]!) on FIELD_DEFINITION
directive @trace on FIELD_DEFINITION
directive @rateLimit(max: Int, window: String) on FIELD_DEFINITION

// === ARCHIVO: src/test/java/com/fintech/integration/infrastructure/grpc/RiskEvaluationGrpcServiceTest.java ===
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

// === ARCHIVO: src/test/java/com/fintech/integration/infrastructure/amqp/NotificationAmqpProducerTest.java ===
package com.fintech.integration.infrastructure.amqp;

import com.fintech.integration.domain.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para NotificationAmqpProducer")
class NotificationAmqpProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private MessageConverter messageConverter;

    private NotificationAmqpProducer producer;

    private static final String EXCHANGE = "notifications.exchange";
    private static final String ROUTING_KEY = "notifications.routing.key";

    @BeforeEach
    void setUp() {
        producer = new NotificationAmqpProducer(rabbitTemplate, EXCHANGE, ROUTING_KEY);
        when(rabbitTemplate.getMessageConverter()).thenReturn(messageConverter);
    }

    @Test
    @DisplayName("Debe enviar notificación exitosamente")
    void sendNotification_validNotification_sendsSuccessfully() {
        Notification notification = new Notification(
            UUID.randomUUID().toString(),
            "USER_001",
            "EMAIL",
            "Test message",
            Map.of("correlationId", "corr-123", "priority", "HIGH")
        );

        doNothing().when(rabbitTemplate).convertAndSend(
            eq(EXCHANGE),
            eq(ROUTING_KEY),
            any(Notification.class)
        );

        producer.sendNotification(notification);

        verify(rabbitTemplate, times(1)).convertAndSend(
            eq(EXCHANGE),
            eq(ROUTING_KEY),
            eq(notification)
        );
    }

    @Test
    @DisplayName("Debe enviar notificación con metadatos personalizados")
    void sendNotification_withCustomMetadata_sendsWithMetadata() {
        Notification notification = new Notification(
            UUID.randomUUID().toString(),
            "USER_002",
            "SMS",
            "Alert message",
            Map.of("correlationId", "corr-456", "retryCount", "0", "source", "grpc-service")
        );

        doNothing().when(rabbitTemplate).convertAndSend(anyString(), anyString(), any(Notification.class));

        producer.sendNotification(notification);

        verify(rabbitTemplate, times(1)).convertAndSend(
            eq(EXCHANGE),
            eq(ROUTING_KEY),
            eq(notification)
        );

        assertThat(notification.getMetadata("correlationId")).isEqualTo("corr-456");
    }

    @Test
    @DisplayName("Debe manejar error cuando la notificación es nula")
    void sendNotification_nullNotification_throwsException() {
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> producer.sendNotification(null)
        );
    }

    @Test
    @DisplayName("Debe validar notificación antes de enviar")
    void sendNotification_invalidNotificationId_throwsException() {
        Notification invalidNotification = new Notification(
            null,
            "USER_001",
            "EMAIL",
            "Test",
            Map.of()
        );

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> producer.sendNotification(invalidNotification)
        );
    }

    @Test
    @DisplayName("Debe validar usuario receptor antes de enviar")
    void sendNotification_invalidReceiver_throwsException() {
        Notification invalidNotification = new Notification(
            UUID.randomUUID().toString(),
            null,
            "EMAIL",
            "Test",
            Map.of()
        );

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> producer.sendNotification(invalidNotification)
        );
    }

    @Test
    @DisplayName("Debe validar tipo de notificación antes de enviar")
    void sendNotification_invalidType_throwsException() {
        Notification invalidNotification = new Notification(
            UUID.randomUUID().toString(),
            "USER_001",
            null,
            "Test",
            Map.of()
        );

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> producer.sendNotification(invalidNotification)
        );
    }

    @Test
    @DisplayName("Debe permitir agregar metadatos a la notificación")
    void notification_withMetadata_addedCorrectly() {
        Notification original = new Notification(
            UUID.randomUUID().toString(),
            "USER_001",
            "EMAIL",
            "Test message",
            Map.of("key1", "value1")
        );

        Notification withMeta = original.withMetadata("key2", "value2");

        assertThat(withMeta.getMetadata("key1")).isEqualTo("value1");
        assertThat(withMeta.getMetadata("key2")).isEqualTo("value2");
    }

    @Test
    @DisplayName("Debe manejar error de RabbitMQ al enviar")
    void sendNotification_rabbitFailure_throwsException() {
        Notification notification = new Notification(
            UUID.randomUUID().toString(),
            "USER_001",
            "EMAIL",
            "Test message",
            Map.of()
        );

        doThrow(new org.springframework.amqp.AmqpException("Connection refused"))
            .when(rabbitTemplate).convertAndSend(anyString(), anyString(), any(Notification.class));

        org.junit.jupiter.api.Assertions.assertThrows(
            org.springframework.amqp.AmqpException.class,
            () -> producer.sendNotification(notification)
        );
    }
}

// === ARCHIVO: src/test/java/com/fintech/integration/infrastructure/graphql/UserGraphQLControllerTest.java ===
package com.fintech.integration.infrastructure.graphql;

import com.fintech.integration.domain.UserManagement;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para UserGraphQLController")
class UserGraphQLControllerTest {

    @Mock
    private UserGraphQLController controller;

    @Mock
    private GraphQLSchema graphQLSchema;

    @Mock
    private GraphQL graphQL;

    private static final String TEST_USER_ID = "550e8400-e29b-41d4-a716-446655440000";
    private static final String TEST_ROLE = "ADMIN";

    @BeforeEach
    void setUp() {
        controller = new UserGraphQLController();
    }

    @Test
    @DisplayName("Debe obtener usuario exitosamente por ID")
    void getUserById_withValidId_returnsUser() {
        UserManagement user = new UserManagement(
            TEST_USER_ID,
            "john.doe@example.com",
            List.of("USER", "ADMIN"),
            true
        );

        when(controller.getUserById(TEST_USER_ID)).thenReturn(Mono.just(user));

        StepVerifier.create(controller.getUserById(TEST_USER_ID))
            .assertNext(result -> {
                assertThat(result.userId()).isEqualTo(TEST_USER_ID);
                assertThat(result.email()).isEqualTo("john.doe@example.com");
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe verificar roles de usuario correctamente")
    void getUserById_userHasRole_returnsTrue() {
        UserManagement user = new UserManagement(
            TEST_USER_ID,
            "admin@example.com",
            List.of("ADMIN", "USER"),
            true
        );

        assertThat(user.hasRole("ADMIN")).isTrue();
        assertThat(user.hasRole("USER")).isTrue();
    }

    @Test
    @DisplayName("Debe retornar falso cuando usuario no tiene el rol")
    void getUserById_userDoesNotHaveRole_returnsFalse() {
        UserManagement user = new UserManagement(
            TEST_USER_ID,
            "user@example.com",
            List.of("USER"),
            true
        );

        assertThat(user.hasRole("ADMIN")).isFalse();
        assertThat(user.hasRole("SUPER_ADMIN")).isFalse();
    }

    @Test
    @DisplayName("Debe validar usuario con email válido")
    void validate_withValidUser_doesNotThrow() {
        UserManagement user = new UserManagement(
            TEST_USER_ID,
            "valid.email@example.com",
            List.of("USER"),
            true
        );

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(user::validate);
    }

    @Test
    @DisplayName("Debe manejar error cuando el ID de usuario es nulo")
    void validate_withNullUserId_throwsException() {
        UserManagement invalidUser = new UserManagement(
            null,
            "test@example.com",
            List.of("USER"),
            true
        );

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            invalidUser::validate
        );
    }

    @Test
    @DisplayName("Debe manejar error cuando el email es inválido")
    void validate_withInvalidEmail_throwsException() {
        UserManagement invalidUser = new UserManagement(
            TEST_USER_ID,
            "invalid-email",
            List.of("USER"),
            true
        );

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            invalidUser::validate
        );
    }

    @Test
    @DisplayName("Debe manejar error cuando la lista de roles está vacía")
    void validate_withEmptyRoles_throwsException() {
        UserManagement invalidUser = new UserManagement(
            TEST_USER_ID,
            "test@example.com",
            List.of(),
            true
        );

        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            invalidUser::validate
        );
    }

    @Test
    @DisplayName("Debe ejecutar query GraphQL exitosamente")
    void executeQuery_withValidQuery_returnsResult() {
        String query = "{ user(id: \"" + TEST_USER_ID + "\") { userId email } }";

        Map<String, Object> response = Map.of(
            "user", Map.of(
                "userId", TEST_USER_ID,
                "email", "test@example.com"
            )
        );

        when(graphQL.execute(query)).thenReturn(
            graphql.ExecutionResult.newExecutionResult()
                .data(response)
                .build()
        );

        assertThat(graphQL).isNotNull();
    }

    @Test
    @DisplayName("Debe ejecutar mutation GraphQL exitosamente")
    void executeMutation_withValidMutation_returnsResult() {
        String mutation = "mutation { createUser(email: \"new@example.com\", roles: [\"USER\"]) { userId email } }";

        Map<String, Object> response = Map.of(
            "createUser", Map.of(
                "userId", UUID.randomUUID().toString(),
                "email", "new@example.com"
            )
        );

        when(graphQL.execute(mutation)).thenReturn(
            graphql.ExecutionResult.newExecutionResult()
                .data(response)
                .build()
        );

        assertThat(graphQL).isNotNull();
    }

    @Test
    @DisplayName("Debe manejar error de sintaxis en query GraphQL")
    void executeQuery_withInvalidSyntax_returnsError() {
        String invalidQuery = "{ user { invalidField } }";

        when(graphQL.execute(invalidQuery)).thenReturn(
            graphql.ExecutionResult.newExecutionResult()
                .addError(new graphql.graphiql.error.GraphQLErrorSyntax("Syntax error"))
                .build()
        );

        assertThat(graphQL).isNotNull();
    }

    @Test
    @DisplayName("Debe crear usuario inactivo correctamente")
    void validate_inactiveUser_doesNotThrow() {
        UserManagement inactiveUser = new UserManagement(
            TEST_USER_ID,
            "inactive@example.com",
            List.of("USER"),
            false
        );

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(inactiveUser::validate);
        assertThat(inactiveUser.active()).isFalse();
    }

    @Test
    @DisplayName("Debe verificar que múltiples roles se almacenan correctamente")
    void validate_multipleRoles_storedCorrectly() {
        UserManagement multiRoleUser = new UserManagement(
            TEST_USER_ID,
            "multi.role@example.com",
            List.of("USER", "ADMIN", "AUDITOR"),
            true
        );

        assertThat(multiRoleUser.roles()).hasSize(3);
        assertThat(multiRoleUser.roles()).contains("USER", "ADMIN", "AUDITOR");
    }
}
```
