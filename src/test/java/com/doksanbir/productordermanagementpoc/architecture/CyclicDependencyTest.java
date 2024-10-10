package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * This test class enforces the detection of cyclic dependencies between architectural layers.
 *
 * Cyclic dependencies can create tight coupling between layers, making the system harder to maintain
 * and evolve. Therefore, this test ensures that there are no circular dependencies between the
 * domain, application, and infrastructure layers.
 *
 * ArchUnit is used to detect cycles by analyzing package dependencies, ensuring that the system
 * architecture remains modular and loosely coupled.
 */
public class CyclicDependencyTest {

    // Base package constant for the system.
    private static final String BASE_PACKAGE = "com.doksanbir.productordermanagementpoc";

    // JavaClasses represents the set of imported classes for architectural checks.
    private static JavaClasses importedClasses;

    /**
     * Imports all classes from the base package "com.doksanbir.productordermanagementpoc" before all test cases.
     *
     * This setup method is executed once before running the test cases, ensuring the necessary classes
     * are available for analysis by ArchUnit.
     */
    @BeforeAll
    static void setup() {
        importedClasses = new ClassFileImporter().importPackages(BASE_PACKAGE);
    }

    /**
     * Use Case 16: Cyclic Dependency Detection
     *
     * Scenario: Ensure that there are no cyclic dependencies between the domain, application, and infrastructure layers.
     *
     * This test checks that there are no circular dependencies between slices of the system, which could
     * hinder maintainability. The system layers (domain, application, and infrastructure) should be independent
     * and free of cycles.
     */
    @Test
    void noCyclicDependenciesBetweenLayers() {
        SlicesRuleDefinition.slices()
                .matching(BASE_PACKAGE + ".(*)..")
                .should().beFreeOfCycles()
                .because("Cyclic dependencies between domain, application, and infrastructure layers are not allowed")
                .check(importedClasses);
    }
}
