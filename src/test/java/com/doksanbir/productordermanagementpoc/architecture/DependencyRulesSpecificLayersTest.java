package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * This test class enforces dependency rules specific to certain layers within the product order management system.
 *
 * The rules enforced include:
 * - Adapters should not depend on each other.
 * - Specification classes should only depend on the domain layer and not on application or infrastructure layers.
 *
 * ArchUnit is used to check for violations of these dependency principles, ensuring proper separation of concerns
 * between layers and maintaining a clean architecture.
 */
public class DependencyRulesSpecificLayersTest {

    // Package constants representing the adapter and application layers.
    private static final String INFRASTRUCTURE_ADAPTER_IN = "com.doksanbir.productordermanagementpoc.infrastructure.adapter.in..";
    private static final String INFRASTRUCTURE_ADAPTER_OUT = "com.doksanbir.productordermanagementpoc.infrastructure.adapter.out..";
    private static final String SPECIFICATION = "com.doksanbir.productordermanagementpoc.application.specification..";

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
     * Use Case 26: Adapter Independence
     *
     * Scenario: Classes within "infrastructure.adapter.in" and "infrastructure.adapter.out" should not depend on
     * each other, maintaining loose coupling between input and output adapters.
     *
     * This test enforces that adapters remain independent and do not depend on each other, ensuring
     * a clear separation between input and output adapters in the infrastructure layer.
     */
    @Test
    void adaptersShouldNotDependOnEachOther() {
        noClasses()
                .that().resideInAnyPackage(INFRASTRUCTURE_ADAPTER_IN, INFRASTRUCTURE_ADAPTER_OUT)
                .should().dependOnClassesThat().resideInAnyPackage(INFRASTRUCTURE_ADAPTER_IN, INFRASTRUCTURE_ADAPTER_OUT)
                .because("Adapters should not depend on each other to prevent tight coupling")
                .check(importedClasses);
    }

    /**
     * Use Case 27: Specification Layer Independence
     *
     * Scenario: Specification classes should only depend on the domain layer and should not depend on the application
     * or infrastructure layers.
     *
     * This test ensures that specification classes only depend on domain classes, maintaining their independence
     * from the application and infrastructure layers.
     */
    @Test
    void specificationClassesShouldOnlyDependOnDomainLayer() {
        noClasses()
                .that().resideInAnyPackage(SPECIFICATION)
                .should().dependOnClassesThat().resideInAnyPackage(
                        "com.doksanbir.productordermanagementpoc.application..",
                        "com.doksanbir.productordermanagementpoc.infrastructure.."
                )
                .because("Specification classes should only depend on the domain layer")
                .check(importedClasses);
    }
}
