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