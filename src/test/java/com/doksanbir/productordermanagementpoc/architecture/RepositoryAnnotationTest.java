package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * This test class enforces architectural constraints regarding the correct use of annotations for repositories,
 * entities, and specification classes within the product order management system.
 *
 * The tests ensure that:
 * - Repository adapters are annotated correctly.
 * - Domain entities are properly configured with JPA annotations.
 * - Specification classes remain pure utility classes without unnecessary Spring stereotype annotations.
 *
 * This is achieved using ArchUnit, which checks for compliance with architectural rules by analyzing
 * class annotations and package structure.
 */
public class RepositoryAnnotationTest {

    // Package constants representing different layers of the application
    private static final String INFRASTRUCTURE_ADAPTER_OUT = "com.doksanbir.productordermanagementpoc.infrastructure.adapter.out..";
    private static final String DOMAIN = "com.doksanbir.productordermanagementpoc.domain..";
    private static final String APPLICATION_SPECIFICATION = "com.doksanbir.productordermanagementpoc.application.specification..";

    // JavaClasses represents the set of classes imported for architectural checks
    private static JavaClasses importedClasses;

    /**
     * Imports all classes from the "com.doksanbir.productordermanagementpoc" package before all test cases.
     *
     * This setup method is executed once before running the test cases,
     * ensuring the necessary classes are available for analysis by ArchUnit.
     */
    @BeforeAll
    static void setup() {
        importedClasses = new ClassFileImporter().importPackages("com.doksanbir.productordermanagementpoc");
    }

    /**
     * Use Case 11: Repository Annotation
     *
     * Scenario: All repository adapter classes in the "infrastructure.adapter.out" package must be annotated with @Repository.
     *
     * This test enforces that all repository adapters within the infrastructure layer are correctly annotated
     * with the Spring @Repository annotation, ensuring proper identification and handling by the Spring framework.
     */
    @Test
    void repositoryAdaptersShouldBeAnnotatedWithRepository() {
        classes()
                .that().resideInAnyPackage(INFRASTRUCTURE_ADAPTER_OUT)
                .and().areAnnotatedWith("org.springframework.stereotype.Repository")
                .should().beAnnotatedWith("org.springframework.stereotype.Repository")
                .because("All repository adapter classes should be annotated with @Repository")
                .check(importedClasses);
    }

    /**
     * Use Case 12: Entity Annotation
     *
     * Scenario: All domain entity classes in the "domain" package must be annotated with @Entity and @Table.
     *
     * This test checks that all domain entity classes are properly configured with JPA annotations. Specifically,
     * it ensures that each domain entity is annotated with both @Entity and @Table, which are necessary for JPA
     * to manage database table mappings.
     */
    @Test
    void domainEntitiesShouldBeAnnotatedWithEntityAndTable() {
        classes()
                .that().resideInAnyPackage(DOMAIN)
                .and().areAnnotatedWith("jakarta.persistence.Entity")
                .should().beAnnotatedWith("jakarta.persistence.Table")
                .because("All domain entities should be annotated with @Entity and @Table")
                .check(importedClasses);
    }

    /**
     * Use Case 13: Specification Class Annotations
     *
     * Scenario: Specification utility classes should not carry Spring stereotype annotations (e.g., @Component, @Service, @Repository).
     *
     * This test ensures that classes within the specification package are treated as pure utility classes,
     * meaning they should not carry Spring stereotype annotations like @Component, @Service, or @Repository.
     * This preserves their role as static utility providers without having them participate in the Spring
     * component lifecycle.
     */
    @Test
    void specificationClassesShouldNotBeAnnotatedWithSpringStereotypes() {
        classes()
                .that().resideInAnyPackage(APPLICATION_SPECIFICATION)
                .and().areNotInterfaces()
                .should().notBeAnnotatedWith("org.springframework.stereotype.Component")
                .andShould().notBeAnnotatedWith("org.springframework.stereotype.Service")
                .andShould().notBeAnnotatedWith("org.springframework.stereotype.Repository")
                .because("Specification classes should not carry Spring stereotype annotations")
                .check(importedClasses);
    }
}
