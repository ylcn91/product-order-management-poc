package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * This test class enforces specific naming conventions for interfaces, services, adapters, and domain entities
 * within the product order management system.
 *
 * Naming conventions ensure consistency and clarity in code, making it easier to understand the role and purpose
 * of each class. This test ensures that:
 * - Use case interfaces end with "UseCase".
 * - Service implementations are suffixed with "Service".
 * - Adapter implementations are suffixed with "Adapter".
 * - Domain entities use singular names following proper capitalization rules.
 */
public class NamingConventionTest {

    // Package constants representing different layers of the application.
    private static final String APPLICATION_PORT_IN_ORDER = "com.doksanbir.productordermanagementpoc.application.port.in.order..";
    private static final String APPLICATION_PORT_IN_PRODUCT = "com.doksanbir.productordermanagementpoc.application.port.in.product..";
    private static final String APPLICATION_SERVICE_ORDER = "com.doksanbir.productordermanagementpoc.application.service.order..";
    private static final String APPLICATION_SERVICE_PRODUCT = "com.doksanbir.productordermanagementpoc.application.service.product..";
    private static final String INFRASTRUCTURE_ADAPTER_OUT_ORDER = "com.doksanbir.productordermanagementpoc.infrastructure.adapter.out.order..";
    private static final String INFRASTRUCTURE_ADAPTER_OUT_PRODUCT = "com.doksanbir.productordermanagementpoc.infrastructure.adapter.out.product..";
    private static final String DOMAIN = "com.doksanbir.productordermanagementpoc.domain..";

    // JavaClasses represents the set of imported classes for architectural checks.
    private static JavaClasses importedClasses;

    /**
     * Imports all classes from the "com.doksanbir.productordermanagementpoc" package before all test cases.
     * This setup method is executed once before running the test cases, ensuring the necessary classes
     * are available for analysis by ArchUnit.
     */
    @BeforeAll
    static void setup() {
        importedClasses = new ClassFileImporter().importPackages("com.doksanbir.productordermanagementpoc");
    }

    /**
     * Use Case 5: Use Case Interface Naming
     * Scenario: All interfaces in "application.port.in.order" and "application.port.in.product" packages
     * should end with "UseCase".
     * This test ensures that all use case interfaces follow the naming convention of being suffixed with "UseCase",
     * promoting consistency and clarity in the interface names.
     */
    @Test
    void useCaseInterfacesShouldBeProperlyNamed() {
        classes()
                .that().resideInAnyPackage(APPLICATION_PORT_IN_ORDER, APPLICATION_PORT_IN_PRODUCT)
                .and().areInterfaces()
                .should().haveSimpleNameEndingWith("UseCase")
                .because("Use case interfaces should be suffixed with 'UseCase'")
                .check(importedClasses);
    }

    /**
     * Use Case 6: Service Implementation Naming
     * Scenario: Service classes in "application.service.order" and "application.service.product" packages
     * should be suffixed with "Service".
     * This test ensures that all service implementation classes follow the naming convention of being suffixed
     * with "Service", making it clear that the class represents a service implementation.
     */
    @Test
    void serviceImplementationsShouldBeProperlyNamed() {
        classes()
                .that().resideInAnyPackage(APPLICATION_SERVICE_ORDER, APPLICATION_SERVICE_PRODUCT)
                .and().areAnnotatedWith("org.springframework.stereotype.Service")
                .should().haveSimpleNameEndingWith("Service")
                .because("Service implementations should be suffixed with 'Service'")
                .check(importedClasses);
    }

    /**
     * Use Case 7: Adapter Implementation Naming
     *
     * Scenario: Adapter classes in "infrastructure.adapter.out.order" and "infrastructure.adapter.out.product"
     * packages should end with "Adapter".
     *
     * This test ensures that adapter implementation classes follow the naming convention of being suffixed
     * with "Adapter", providing consistency and clarity in the adapter implementation names.
     */
    @Test
    void adapterImplementationsShouldBeProperlyNamed() {
        classes()
                .that().resideInAnyPackage(INFRASTRUCTURE_ADAPTER_OUT_ORDER, INFRASTRUCTURE_ADAPTER_OUT_PRODUCT)
                .and().areAnnotatedWith("org.springframework.stereotype.Repository")
                .should().haveSimpleNameEndingWith("Adapter")
                .because("Adapter implementations should be suffixed with 'Adapter'")
                .check(importedClasses);
    }

    /**
     * Use Case 8: Domain Entity Naming
     *
     * Scenario: Domain entities in the domain package should use singular noun names and follow proper capitalization.
     *
     * This test ensures that all domain entities use singular noun names, avoiding pluralization, and follow
     * consistent capitalization rules to make the naming of domain objects clear and understandable.
     */
    @Test
    void domainEntitiesShouldHaveSingularNames() {
        classes()
                .that().resideInAnyPackage(DOMAIN)
                .and().areAnnotatedWith("jakarta.persistence.Entity")
                .should().haveSimpleNameNotEndingWith("s") // Simple check for singularity
                .because("Domain entities should use singular noun names")
                .check(importedClasses);
    }
}
