# Comparativa de protocolos de comunicación en arquitecturas de microservicios

El sistema de microservicios de una plataforma fintech requiere la implementación de diferentes protocolos de comunicación para optimizar la eficiencia y flexibilidad en la interacción entre servicios. Los servicios incluyen un motor de evaluación de riesgos, un sistema de gestión de usuarios y un servicio de notificaciones. El objetivo es comparar y contrastar los protocolos RSocket, HTTP/1, HTTP/2, WebSocket, gRPC, AMQP y GraphQL en términos de rendimiento, manejo de mensajes, flexibilidad y casos de uso adecuados.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Protocolos de comunicación no convencionales |
| **Nivel** | advanced-l2 |
| **Tipo** | theoretical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Exploración de protocolos de comunicación

**Objetivo:** Comprender las diferencias y similitudes entre RSocket, HTTP/1, HTTP/2, WebSocket, gRPC, AMQP y GraphQL.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar las características principales de cada protocolo.
- Discutir los casos de uso adecuados para cada uno.
- Evaluar la eficiencia y flexibilidad de cada protocolo en el contexto de microservicios.

**Entregable:** Resumen comparativo de los protocolos de comunicación.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la latencia, el throughput y la complejidad de implementación de cada protocolo.
- Piensa en cómo cada protocolo maneja la consistencia y la disponibilidad en un sistema distribuido.

</details>

### Fase 2: Evaluación de gRPC en microservicios

**Objetivo:** Analizar las ventajas y desventajas de usar gRPC en una arquitectura de microservicios.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Discutir cómo gRPC facilita la comunicación entre servicios.
- Evaluar el rendimiento y la flexibilidad de gRPC en comparación con REST.
- Identificar posibles desafíos y soluciones al implementar gRPC.

**Entregable:** Análisis detallado de gRPC en el contexto de microservicios.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la serialización de datos y el manejo de errores en gRPC.
- Piensa en cómo gRPC puede mejorar la latencia y el throughput en comparación con REST.

</details>

### Fase 3: Manejo de mensajes y cola en AMQP

**Objetivo:** Entender cómo AMQP maneja los mensajes y la cola en un sistema de microservicios.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Discutir las características de AMQP en términos de manejo de mensajes y cola.
- Evaluar la eficiencia y fiabilidad de AMQP en un sistema distribuido.
- Identificar posibles desafíos y soluciones al implementar AMQP.

**Entregable:** Análisis detallado del manejo de mensajes y cola en AMQP.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la garantía de entrega de mensajes y el manejo de errores en AMQP.
- Piensa en cómo AMQP puede mejorar la fiabilidad y la eficiencia en la comunicación entre servicios.

</details>

### Fase 4: Rendimiento y flexibilidad de GraphQL

**Objetivo:** Comprender el rendimiento y flexibilidad de GraphQL en comparación con REST.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Discutir las ventajas y desventajas de GraphQL en términos de rendimiento y flexibilidad.
- Evaluar cómo GraphQL puede mejorar la eficiencia y la flexibilidad en la comunicación entre servicios.
- Identificar posibles desafíos y soluciones al implementar GraphQL.

**Entregable:** Análisis detallado del rendimiento y flexibilidad de GraphQL.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la serialización de datos y el manejo de errores en GraphQL.
- Piensa en cómo GraphQL puede reducir la latencia y mejorar la flexibilidad en comparación con REST.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Cuáles son las características principales de cada protocolo de comunicación?
- **paraQueSirve**: ¿En qué escenarios es más adecuado usar cada protocolo?
- **comoSeUsa**: ¿Cómo se implementa cada protocolo en una arquitectura de microservicios?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar cada protocolo?
- **queDecisionesImplica**: ¿Qué decisiones arquitectónicas implica la elección de cada protocolo?

## Criterios de Evaluacion

- Comprensión de las diferencias y similitudes entre los protocolos de comunicación.
- Análisis detallado de gRPC en el contexto de microservicios.
- Entendimiento del manejo de mensajes y cola en AMQP.
- Evaluación del rendimiento y flexibilidad de GraphQL en comparación con REST.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
npx --yes @redocly/cli lint openapi.yaml
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
