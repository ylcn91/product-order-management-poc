package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * This test class enforces specific package structure rules within the product order management system.
 *
 * The package structure ensures that:
 * - Service classes reside in appropriate service packages.
 * - Adapter classes (input and output) are placed in the designated adapter packages.
 * - Domain entities are properly organized within the domain package.
 *
 * ArchUnit is used to check for compliance with the defined architectural structure by analyzing package
 * locations and the annotations applied to classes.
 */
public class PackageStructureTest {

    // Package constants representing different layers of the application.
    private static final String APPLICATION_SERVICE = "com.doksanbir.productordermanagementpoc.application.service..";
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
     * Use Case 18: Service Class Location
     *
     * Scenario: All service implementation classes must reside within the "application.service.order"
     * or "application.service.product" packages.
     *
     * This test ensures that all service classes, identified by the @Service annotation, are correctly
     * located within the designated service packages.
     */
    @Test
    void serviceClassesShouldResideInServicePackages() {
        classes()
                .that().resideInAnyPackage("com.doksanbir.productordermanagementpoc.application.service..")
                .and().areAnnotatedWith("org.springframework.stereotype.Service")
                .should().resideInAnyPackage(APPLICATION_SERVICE)
                .because("All service implementation classes must be located within application.service.order or application.service.product")
                .check(importedClasses);
    }

    /**
     * Use Case 19: Adapter Class Location
     *
     * Scenario: All adapter classes must be placed within the "infrastructure.adapter.in"
     * or "infrastructure.adapter.out" packages.
     *
     * This test enforces the rule that input and output adapter classes, annotated with
     * @RestController or @Repository, should reside within the appropriate adapter packages.
     */
    @Test
    void adapterClassesShouldResideInAdapterPackages() {
        classes()
                .that().resideInAnyPackage("com.doksanbir.productordermanagementpoc.infrastructure.adapter..")
                .and().areAnnotatedWith("org.springframework.stereotype.Repository")
                .or().areAnnotatedWith("org.springframework.web.bind.annotation.RestController")
                .should().resideInAnyPackage(INFRASTRUCTURE_ADAPTER_IN, INFRASTRUCTURE_ADAPTER_OUT)
                .because("All adapter classes must be placed within infrastructure.adapter.in or infrastructure.adapter.out packages")
                .check(importedClasses);
    }

    /**
     * Use Case 20: Domain Entity Location
     *
     * Scenario: All domain entities must be exclusively located within the domain package.
     *
     * This test ensures that all domain entities, identified by the @Entity annotation,
     * are located within the "domain" package, adhering to proper domain-driven design principles.
     */
    @Test
    void domainEntitiesShouldResideInDomainPackage() {
        classes()
                .that().areAnnotatedWith("jakarta.persistence.Entity")
                .should().resideInAnyPackage(DOMAIN)
                .because("All domain entities must be exclusively located within the domain package")
                .check(importedClasses);
    }
}
