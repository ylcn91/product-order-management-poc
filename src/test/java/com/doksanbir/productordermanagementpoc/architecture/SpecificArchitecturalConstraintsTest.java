package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * This test class enforces specific architectural constraints for the product order management system.
 *
 * The constraints ensure proper layering and adherence to design patterns like the strategy pattern,
 * service-repository dependency rules, and package boundaries for specifications and exceptions.
 *
 * This test is implemented using ArchUnit, a library for checking the architecture of a codebase.
 * The goal is to maintain consistency in how the project enforces its architecture and to prevent
 * violations like service classes depending on concrete repositories or misplacement of strategy implementations.
 *
 * The class makes use of ArchUnit's declarative syntax to define the architectural rules, and each
 * rule is checked against the imported classes from the codebase.
 */
public class SpecificArchitecturalConstraintsTest {

    /**
     * Enum to manage package names within the product order management project.
     * Each package is associated with a specific layer or responsibility, including:
     * - Strategy implementations for order processing
     * - Service layers for order and product management
     * - Port interfaces for input and output operations
     * - Domain entities
     * - Specification classes for dynamic query generation
     * - Exception handling classes
     *
     * The enum provides a central place to manage and reference package names to avoid
     * hardcoded strings and maintain consistency in package structure.
     */
    @Getter
    private enum PackageName {
        STRATEGY_ORDER("com.doksanbir.productordermanagementpoc.application.strategy.order.."),
        SERVICE_ORDER("com.doksanbir.productordermanagementpoc.application.service.order.."),
        SERVICE_PRODUCT("com.doksanbir.productordermanagementpoc.application.service.product.."),
        PORT_OUT("com.doksanbir.productordermanagementpoc.application.port.out.."),
        PORT_IN("com.doksanbir.productordermanagementpoc.application.port.in.."),
        DOMAIN("com.doksanbir.productordermanagementpoc.domain.."),
        SPECIFICATION_ORDER("com.doksanbir.productordermanagementpoc.application.specification.order.."),
        SPECIFICATION_PRODUCT("com.doksanbir.productordermanagementpoc.application.specification.product.."),
        EXCEPTION("com.doksanbir.productordermanagementpoc.exception..");

        private final String value;

        PackageName(String value) {
            this.value = value;
        }
    }

    /**
     * JavaClasses represents the set of imported classes from the project that will be
     * checked against the defined architectural rules.
     */
    private static JavaClasses importedClasses;

    /**
     * Sets up the test by importing all classes from the "com.doksanbir.productordermanagementpoc" package.
     *
     * This method is executed once before all test cases, importing the necessary classes
     * for the architecture tests.
     */
    @BeforeAll
    static void setup() {
        importedClasses = new ClassFileImporter().importPackages("com.doksanbir.productordermanagementpoc");
    }

    /**
     * Enforces that all implementations of the "OrderProcessingStrategy" interface must:
     * - Reside in the package "com.doksanbir.productordermanagementpoc.application.strategy.order"
     * - Have a class name ending with "OrderProcessingStrategy"
     *
     * This ensures that strategy pattern implementations related to order processing are correctly
     * placed in the strategy package and follow consistent naming conventions.
     */
    @Test
    void strategyImplementationsShouldResideInCorrectPackageAndHandleSpecificStatus() {
        ArchRule rule = classes()
                .that().implement("com.doksanbir.productordermanagementpoc.application.strategy.order.OrderProcessingStrategy")
                .should().resideInAnyPackage(PackageName.STRATEGY_ORDER.getValue())
                .andShould().haveSimpleNameEndingWith("OrderProcessingStrategy")
                .because("All OrderProcessingStrategy implementations should reside in application.strategy.order and follow naming conventions");

        rule.check(importedClasses);
    }

    /**
     * Enforces that service classes must only depend on:
     * - Repository port interfaces (i.e., interfaces in the "port.out" package)
     * - Domain entities
     * - Strategy and specification classes for query building
     * - Standard Java, javax, and Spring libraries
     * - Exception handling classes
     *
     * This prevents service classes from depending on concrete implementations of repositories or other layers,
     * ensuring adherence to the dependency inversion principle. By enforcing this rule, service classes remain
     * decoupled from the lower layers, relying only on port interfaces for persistence.
     */
    @Test
    void serviceClassesShouldOnlyDependOnRepositoryPortInterfaces() {
        ArchRule rule = classes()
                .that().resideInAnyPackage(PackageName.SERVICE_ORDER.getValue(), PackageName.SERVICE_PRODUCT.getValue())
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage(
                        PackageName.PORT_OUT.getValue(),
                        PackageName.PORT_IN.getValue(),
                        PackageName.DOMAIN.getValue(),
                        PackageName.STRATEGY_ORDER.getValue(),
                        PackageName.SPECIFICATION_ORDER.getValue(),
                        PackageName.SPECIFICATION_PRODUCT.getValue(),
                        PackageName.EXCEPTION.getValue(),
                        "java..",
                        "javax..",
                        "org.springframework..",
                        "org.slf4j..",
                        "lombok.."
                )
                .because("Service classes should only depend on repository port interfaces, use case interfaces, and standard libraries");

        rule.check(importedClasses);
    }
}
