package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * This test class enforces layer dependency rules within the product order management system.
 *
 * The architecture follows a layered approach where dependencies between layers are restricted to ensure
 * a clean separation of concerns. Specifically:
 * - Application layer (ports) should not depend on infrastructure.
 * - Infrastructure layer (adapters) should only depend on application ports, not concrete implementations.
 * - Domain layer should remain independent of both application and infrastructure.
 * - Controllers should depend only on application ports, not directly on services or repositories.
 *
 * These rules are enforced using ArchUnit, which checks for violations of these dependency principles.
 */
public class LayerDependencyTest {

    // Package constants representing different layers of the application.
    private static final String APPLICATION_PORT_IN = "com.doksanbir.productordermanagementpoc.application.port.in..";
    private static final String APPLICATION_PORT_OUT = "com.doksanbir.productordermanagementpoc.application.port.out..";
    private static final String INFRASTRUCTURE_ADAPTER_IN = "com.doksanbir.productordermanagementpoc.infrastructure.adapter.in..";
    private static final String INFRASTRUCTURE_ADAPTER_OUT = "com.doksanbir.productordermanagementpoc.infrastructure.adapter.out..";
    private static final String DOMAIN = "com.doksanbir.productordermanagementpoc.domain..";

    // JavaClasses represents the set of imported classes for architectural checks.
    private static JavaClasses importedClasses;

    /**
     * Imports all classes from the "com.doksanbir.productordermanagementpoc" package before all test cases.
     *
     * This setup method is executed once before running the test cases, ensuring the necessary classes
     * are available for analysis by ArchUnit.
     */
    @BeforeAll
    static void setup() {
        importedClasses = new ClassFileImporter().importPackages("com.doksanbir.productordermanagementpoc");
    }

    /**
     * Use Case 1: Application Layer Isolation
     *
     * Scenario: Classes within "application.port.in" and "application.port.out" packages should not depend on
     * classes in the "infrastructure.adapter.in" or "infrastructure.adapter.out" packages.
     *
     * This test enforces the rule that the application layer should be isolated from the infrastructure layer,
     * ensuring that application ports are not dependent on concrete infrastructure adapters.
     */
    @Test
    void applicationLayerShouldNotDependOnInfrastructureAdapters() {
        noClasses()
                .that().resideInAnyPackage(APPLICATION_PORT_IN, APPLICATION_PORT_OUT)
                .should().dependOnClassesThat().resideInAnyPackage(INFRASTRUCTURE_ADAPTER_IN, INFRASTRUCTURE_ADAPTER_OUT)
                .because("Application ports should be independent of infrastructure adapters")
                .check(importedClasses);
    }

    /**
     * Use Case 2: Infrastructure Layer Dependency
     *
     * Scenario: Classes in "infrastructure.adapter.in" and "infrastructure.adapter.out" should only depend on
     * interfaces defined in "application.port.in" and "application.port.out", not on concrete implementations or other infrastructure adapters.
     *
     * This test ensures that infrastructure adapters only depend on application port interfaces and standard libraries,
     * maintaining a clean separation of concerns between layers.
     */
    @Test
    void infrastructureAdaptersShouldOnlyDependOnApplicationPorts() {
        classes()
                .that().resideInAnyPackage(INFRASTRUCTURE_ADAPTER_IN, INFRASTRUCTURE_ADAPTER_OUT)
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage(
                        APPLICATION_PORT_IN,
                        APPLICATION_PORT_OUT,
                        "java..",
                        "javax..",
                        "org.springframework.."
                )
                .because("Infrastructure adapters should only depend on application ports and standard libraries")
                .check(importedClasses);
    }

    /**
     * Use Case 3: Domain Layer Purity
     *
     * Scenario: Classes in the "domain" package should remain independent and not depend on any classes from the
     * application or infrastructure layers.
     *
     * This test enforces that domain classes remain pure, free from dependencies on the application or
     * infrastructure layers, adhering to domain-driven design principles.
     */
    @Test
    void domainLayerShouldBeIndependent() {
        noClasses()
                .that().resideInAnyPackage(DOMAIN)
                .should().dependOnClassesThat().resideInAnyPackage(
                        "com.doksanbir.productordermanagementpoc.application..",
                        "com.doksanbir.productordermanagementpoc.infrastructure.."
                )
                .because("Domain layer should be independent of application and infrastructure layers")
                .check(importedClasses);
    }

    /**
     * Use Case 4: Controller Dependencies
     *
     * Scenario: Controller classes in "infrastructure.adapter.in" should only depend on use case interfaces
     * from "application.port.in" and not directly on service implementations or repository ports.
     *
     * This test ensures that controllers only depend on the input ports (use case interfaces) and standard libraries,
     * avoiding direct dependencies on services or repositories, which could violate the clean architecture principles.
     */
    @Test
    void controllersShouldOnlyDependOnApplicationPortIn() {
        classes()
                .that().resideInAnyPackage(INFRASTRUCTURE_ADAPTER_IN)
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage(
                        APPLICATION_PORT_IN,
                        "com.doksanbir.productordermanagementpoc.infrastructure.adapter.in..",
                        "java..",
                        "javax..",
                        "org.springframework.."
                )
                .because("Controllers should only depend on application port in interfaces and standard libraries")
                .check(importedClasses);
    }
}
