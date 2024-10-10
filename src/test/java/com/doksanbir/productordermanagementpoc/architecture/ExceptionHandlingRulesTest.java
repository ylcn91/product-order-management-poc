package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * This test class enforces exception handling rules and related architectural principles within
 * the product order management system.
 *
 * The rules include:
 * - Ensuring that exception classes are properly isolated in their own package.
 * - Restricting the use of @ControllerAdvice to the global exception handler.
 * - Enforcing domain entity inheritance from a common base entity for audit purposes.
 * - Ensuring entity listeners are properly annotated and located in the domain package.
 *
 * ArchUnit is used to check for violations of these architectural rules, ensuring consistency
 * in how exceptions and domain entities are handled.
 */
public class ExceptionHandlingRulesTest {

    // Package constants representing the exception package and other relevant layers.
    private static final String EXCEPTION_PACKAGE = "com.doksanbir.productordermanagementpoc.exception..";
    //TODO: create a test for the global exception handler
    private static final String GLOBAL_EXCEPTION_HANDLER = "com.doksanbir.productordermanagementpoc.exception.GlobalExceptionHandler";
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
     * Use Case: Exception Handling Package Isolation
     *
     * Scenario: Exception classes should reside in the exception package and not depend on other layers
     * to ensure proper isolation of exception handling logic.
     *
     * This test ensures that exception classes only depend on other classes within the exception package,
     * the Java standard libraries, and not on any other layers such as domain or application.
     */
    @Test
    void exceptionClassesShouldResideInExceptionPackageAndNotDependOnOtherLayers() {
        classes()
                .that().resideInAnyPackage(EXCEPTION_PACKAGE)
                .and().areNotInterfaces()
                .should().onlyDependOnClassesThat().resideInAnyPackage(
                        EXCEPTION_PACKAGE,
                        "java.lang..",
                        "java.util.."
                )
                .because("Exception classes should reside in the exception package and not depend on other layers")
                .check(importedClasses);
    }

    /**
     * Use Case: Global Exception Handler Enforcement
     *
     * Scenario: Only the GlobalExceptionHandler should be annotated with @ControllerAdvice, enforcing
     * centralized exception handling across the application.
     *
     * This test ensures that no other class apart from GlobalExceptionHandler uses the @ControllerAdvice annotation,
     * maintaining a single global handler for all exceptions.
     */
    @Test
    void onlyGlobalExceptionHandlerShouldBeAnnotatedWithControllerAdvice() {
        classes()
                .that().areAnnotatedWith("org.springframework.web.bind.annotation.ControllerAdvice")
                .should().haveSimpleName("GlobalExceptionHandler")
                .because("Only GlobalExceptionHandler should be annotated with @ControllerAdvice")
                .check(importedClasses);
    }

    /**
     * Use Case: Domain Entity Inheritance from BaseEntity
     *
     * Scenario: All domain entities must extend BaseEntity to inherit common audit fields and lifecycle callbacks.
     *
     * This test ensures that all classes annotated with @Entity extend BaseEntity, ensuring they
     * inherit common functionality such as audit fields (createdAt, updatedAt) and lifecycle callbacks.
     */
    @Test
    void domainEntitiesShouldExtendBaseEntity() {
        classes()
                .that().areAnnotatedWith("jakarta.persistence.Entity")
                .should().beAssignableTo("com.doksanbir.productordermanagementpoc.domain.BaseEntity")
                .because("All domain entities must extend BaseEntity to inherit common audit fields and lifecycle callbacks")
                .check(importedClasses);
    }

    /**
     * Use Case: Entity Listener Location and Annotation
     *
     * Scenario: Entity listeners should be properly annotated and located within the domain package, ensuring
     * they are associated with domain entities.
     *
     * This test checks that all classes annotated with @EntityListeners reside within the domain package
     * and are properly annotated, ensuring they manage entity lifecycle events without dependencies on other layers.
     */
    @Test
    void entityListenersShouldBeProperlyAnnotatedAndLocated() {
        classes()
                .that().areAnnotatedWith("jakarta.persistence.EntityListeners")
                .should().resideInAnyPackage(DOMAIN)
                .because("Entity listeners should be properly annotated and located within the domain package without dependencies on other layers")
                .check(importedClasses);
    }
}
