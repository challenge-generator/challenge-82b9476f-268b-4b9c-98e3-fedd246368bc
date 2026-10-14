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