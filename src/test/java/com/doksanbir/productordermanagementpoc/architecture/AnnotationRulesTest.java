package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * This test class enforces annotation rules within the product order management system.
 *
 * Annotation rules ensure that the proper Spring annotations are applied to service and controller
 * classes, which allows Spring to manage these components appropriately.
 *
 * ArchUnit is used to check for the correct application of @Service and @RestController annotations,
 * enforcing that services and controllers are correctly identified by the framework.
 */
public class AnnotationRulesTest {

    // Package constants representing the service and adapter (controller) layers.
    private static final String APPLICATION_SERVICE = "com.doksanbir.productordermanagementpoc.application.service..";
    private static final String INFRASTRUCTURE_ADAPTER_IN = "com.doksanbir.productordermanagementpoc.infrastructure.adapter.in..";

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
     * Use Case 9: Service Annotation
     *
     * Scenario: All service classes in "application.service" must be annotated with @Service.
     *
     * This test ensures that all service classes within the application service package are annotated
     * with the @Service annotation, which allows Spring to manage these components as part of the
     * service layer.
     */
    @Test
    void serviceClassesShouldBeAnnotatedWithService() {
        classes()
                .that().resideInAnyPackage(APPLICATION_SERVICE)
                .and().areAnnotatedWith("org.springframework.stereotype.Service")
                .should().beAnnotatedWith("org.springframework.stereotype.Service")
                .because("All service classes should be annotated with @Service")
                .check(importedClasses);
    }

    /**
     * Use Case 10: Controller Annotation
     *
     * Scenario: All controller classes in "infrastructure.adapter.in" must be annotated with @RestController.
     *
     * This test ensures that all controller classes within the infrastructure adapter package
     * (input adapter layer) are annotated with the @RestController annotation, which allows Spring
     * to handle them as REST endpoints.
     */
    @Test
    void controllerClassesShouldBeAnnotatedWithRestController() {
        classes()
                .that().resideInAnyPackage(INFRASTRUCTURE_ADAPTER_IN)
                .and().areAnnotatedWith("org.springframework.web.bind.annotation.RestController")
                .should().beAnnotatedWith("org.springframework.web.bind.annotation.RestController")
                .because("All controller classes should be annotated with @RestController")
                .check(importedClasses);
    }
}
