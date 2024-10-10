package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * ArchUnit tests to enforce dependency inversion and interface implementations.
 */
public class DependencyInversionTest {

    private static final String APPLICATION_SERVICE = "com.doksanbir.productordermanagementpoc.application.service..";
    private static final String APPLICATION_PORT_IN = "com.doksanbir.productordermanagementpoc.application.port.in..";
    private static final String APPLICATION_PORT_OUT = "com.doksanbir.productordermanagementpoc.application.port.out..";
    private static final String INFRASTRUCTURE_ADAPTER_OUT = "com.doksanbir.productordermanagementpoc.infrastructure.adapter.out..";

    private static JavaClasses importedClasses;

    @BeforeAll
    static void setup() {
        importedClasses = new ClassFileImporter().importPackages("com.doksanbir.productordermanagementpoc");
    }

    /**
     * Use Case 14: Service Interface Implementation
     * Scenario: Service classes must implement all corresponding use case interfaces from application.port.in.
     */
    @Test
    void serviceClassesShouldImplementUseCaseInterfaces() {
        classes()
                .that().resideInAnyPackage(APPLICATION_SERVICE)
                .and().areAnnotatedWith("org.springframework.stereotype.Service")
                .should().implement(
                        "com.doksanbir.productordermanagementpoc.application.port.in..*UseCase"
                )
                .because("Service classes should implement all corresponding use case interfaces from application.port.in")
                .check(importedClasses);
    }

    /**
     * Use Case 15: Repository Adapter Implementation
     * Scenario: Adapter classes must implement their respective repository port interfaces from application.port.out.
     */
    @Test
    void repositoryAdaptersShouldImplementRepositoryPortInterfaces() {
        classes()
                .that().resideInAnyPackage(INFRASTRUCTURE_ADAPTER_OUT)
                .and().areAnnotatedWith("org.springframework.stereotype.Repository")
                .should().implement(
                        "com.doksanbir.productordermanagementpoc.application.port.out..*Repository"
                )
                .because("Repository adapter classes should implement their respective repository port interfaces from application.port.out")
                .check(importedClasses);
    }
}
