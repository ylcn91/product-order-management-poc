package com.doksanbir.productordermanagementpoc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * This test class is used to validate architectural rules for the
 * product order management system using ArchUnit.
 *
 * The rules enforced in this class ensure proper layering of the system,
 * and prevent unwanted dependencies or exposure between different layers.
 */
public class AccessRulesTest {

    // Package constants for different application layers
    private static final String DOMAIN = "com.doksanbir.productordermanagementpoc.domain..";
    private static final String APPLICATION = "com.doksanbir.productordermanagementpoc.application..";
    private static final String INFRASTRUCTURE = "com.doksanbir.productordermanagementpoc.infrastructure..";

    // JavaClasses representing the imported classes from the package
    private static JavaClasses importedClasses;

    /**
     * Sets up the test by importing the classes from the specified package.
     * This is done before all test cases are executed.
     */
    @BeforeAll
    static void setup() {
        importedClasses = new ClassFileImporter().importPackages("com.doksanbir.productordermanagementpoc");
    }

    /**
     * Ensures that domain entities are not exposed outside the domain package.
     * The rule checks that no classes outside the domain package can access
     * classes residing inside the domain package.
     */
    @Test
    void domainEntitiesShouldNotBeExposedOutsideDomainPackage() {
        noClasses()
                .that().resideOutsideOfPackage(DOMAIN)
                .should().accessClassesThat().resideInAnyPackage(DOMAIN)
                .because("Domain entities should not be exposed outside the domain package")
                .check(importedClasses);
    }

    /**
     * Ensures that higher layers (such as the infrastructure) do not access
     * package-private classes of lower layers, particularly the application layer.
     * This enforces proper encapsulation and access control between layers.
     */
    @Test
    void higherLayersShouldNotHavePackagePrivateAccessToLowerLayers() {
        noClasses()
                .that().resideInAnyPackage(INFRASTRUCTURE)
                .should().accessClassesThat(arePackagePrivate())
                .andShould().accessClassesThat().resideInAnyPackage(APPLICATION)
                .because("Higher layers should not access package-private classes in lower layers")
                .check(importedClasses);
    }

    /**
     * Ensures that the domain layer does not depend on the application or infrastructure layers.
     * This rule enforces the principle of domain-driven design, ensuring that the domain layer
     * remains pure and independent from external layers.
     */
    @Test
    void domainLayerShouldNotDependOnApplicationOrInfrastructure() {
        noClasses()
                .that().resideInAnyPackage(DOMAIN)
                .should().dependOnClassesThat().resideInAnyPackage(APPLICATION, INFRASTRUCTURE)
                .because("The domain layer should not depend on the application or infrastructure layers")
                .check(importedClasses);
    }

    /**
     * Ensures that infrastructure classes do not access domain classes directly,
     * reinforcing the separation of layers. The infrastructure should interact
     * with the domain only through the application layer.
     */
    @Test
    void infrastructureShouldNotAccessDomainDirectly() {
        noClasses()
                .that().resideInAnyPackage(INFRASTRUCTURE)
                .should().accessClassesThat().resideInAnyPackage(DOMAIN)
                .because("The infrastructure layer should not access domain entities directly. Instead, it should interact through the application layer.")
                .check(importedClasses);
    }

    /**
     * A utility method that creates a predicate for checking whether a class
     * has package-private access (i.e., it is not public, protected, or private).
     *
     * @return DescribedPredicate for checking package-private access.
     */
    private static DescribedPredicate<JavaClass> arePackagePrivate() {
        return new DescribedPredicate<JavaClass>("are package private") {
            @Override
            public boolean test(JavaClass input) {
                return !input.getModifiers().contains(JavaModifier.PUBLIC)
                        && !input.getModifiers().contains(JavaModifier.PROTECTED)
                        && !input.getModifiers().contains(JavaModifier.PRIVATE);
            }
        };
    }

    /**
     * Ensures that application services are not exposed outside the application package.
     * This rule enforces the principle of encapsulation, ensuring that application services
     * are not accessed directly by layers that should not interact with them.
     */
    @Test
    void applicationServicesShouldNotBeExposedOutsideApplicationPackage() {
        noClasses()
                .that().resideOutsideOfPackage(APPLICATION)
                .should().accessClassesThat().resideInAnyPackage(APPLICATION)
                .because("Application services should not be exposed outside the application package")
                .check(importedClasses);
    }

    /**
     * Ensures that only domain classes should access domain repositories directly.
     * The purpose of this rule is to make sure that domain repositories are only interacted
     * with by the domain layer, enforcing a clean separation of concerns.
     */
    @Test
    void onlyDomainClassesShouldAccessDomainRepositories() {
        noClasses()
                .that().resideOutsideOfPackage(DOMAIN)
                .should().accessClassesThat().resideInAnyPackage(DOMAIN + ".repository..")
                .because("Only domain classes should access domain repositories directly")
                .check(importedClasses);
    }
}
