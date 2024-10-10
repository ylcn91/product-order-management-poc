package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noMethods;

/**
 * This test class enforces immutability constraints for domain objects, particularly value objects.
 *
 * Immutability is a core principle in domain-driven design, especially for value objects.
 * Value objects should be immutable, meaning:
 * - All fields should be declared as final.
 * - No setter methods should be provided.
 *
 * ArchUnit is used to check that value objects follow these immutability constraints, ensuring that
 * domain objects remain consistent and free from unexpected modifications.
 */
public class ImmutabilityConstraintsTest {

    // Package representing value objects in the domain layer.
    private static final String VALUE_OBJECTS_PACKAGE = "com.doksanbir.productordermanagementpoc.domain.valueobject..";

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
     * Use Case 38: Immutable Domain Objects
     *
     * Scenario: Certain domain classes, such as value objects, should be immutable. Enforce that these classes
     * have all fields declared as final and do not provide any setter methods.
     *
     * This test checks that all fields in value objects are declared as final, ensuring that the value objects
     * are immutable and cannot be modified after construction.
     */
    @Test
    void immutableDomainObjectsShouldHaveFinalFieldsAndNoSetters() {
        // Check that all fields in value objects are final
        fields()
                .that().areDeclaredInClassesThat().resideInAnyPackage(VALUE_OBJECTS_PACKAGE)
                .should().beFinal()
                .because("Fields in immutable domain objects should be final")
                .check(importedClasses);

        // Check that classes in value objects do not have setter methods
        noMethods()
                .that().areDeclaredInClassesThat().resideInAnyPackage(VALUE_OBJECTS_PACKAGE)
                .should().haveNameStartingWith("set")
                .because("Immutable domain objects should not have setter methods")
                .check(importedClasses);
    }
}
