package com.doksanbir.productordermanagementpoc;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

public class ArchitectureTests {

    private final JavaClasses importedClasses = new ClassFileImporter()
            .importPackages("com.doksanbir.productordermanagementpoc");

    @Test
    void application_services_should_only_depend_on_ports_and_domain() {
        ArchRule rule = classes()
                .that().resideInAPackage("..application.service..")
                .should().onlyDependOnClassesThat(
                        resideInAnyPackage(
                                "com.doksanbir.productordermanagementpoc.application.port..",
                                "com.doksanbir.productordermanagementpoc.domain..",
                                "java.."
                        )
                );

        rule.check(importedClasses);
    }

    @Test
    void controllers_should_only_depend_on_application_ports() {
        ArchRule rule = classes()
                .that().resideInAPackage("..infrastructure.adapter.in..")
                .should().onlyDependOnClassesThat(
                        resideInAnyPackage(
                                "com.doksanbir.productordermanagementpoc.application.port.in..",
                                "com.doksanbir.productordermanagementpoc.domain..",
                                "java.."
                        )
                );

        rule.check(importedClasses);
    }

    @Test
    void repositories_should_only_depend_on_application_ports_and_domain() {
        ArchRule rule = classes()
                .that().resideInAPackage("..infrastructure.adapter.out..")
                .should().onlyDependOnClassesThat(
                        resideInAnyPackage(
                                "com.doksanbir.productordermanagementpoc.application.port.out..",
                                "com.doksanbir.productordermanagementpoc.domain..",
                                "java.."
                        )
                );

        rule.check(importedClasses);
    }

    @Test
    void domain_should_not_depend_on_any_other_layer() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "com.doksanbir.productordermanagementpoc.application..",
                        "com.doksanbir.productordermanagementpoc.infrastructure..",
                        "com.doksanbir.productordermanagementpoc.tests.."
                );

        rule.check(importedClasses);
    }

    @Test
    void services_should_have_service_annotation() {
        ArchRule rule = classes()
                .that().resideInAPackage("..application.service..")
                .should().beAnnotatedWith(org.springframework.stereotype.Service.class);

        rule.check(importedClasses);
    }

    @Test
    void repositories_should_have_repository_annotation() {
        ArchRule rule = classes()
                .that().resideInAPackage("..infrastructure.adapter.out..")
                .should().beAnnotatedWith(org.springframework.stereotype.Repository.class);

        rule.check(importedClasses);
    }

    @Test
    void controllers_should_have_restcontroller_annotation() {
        ArchRule rule = classes()
                .that().resideInAPackage("..infrastructure.adapter.in..")
                .should().beAnnotatedWith(org.springframework.web.bind.annotation.RestController.class);

        rule.check(importedClasses);
    }


    @Test
    void loggers_should_be_private_static_final() {
        ArchRule rule = fields()
                .that().areDeclaredInClassesThat().resideInAPackage("..application.service..")
                .and().haveName("log")
                .should().bePrivate()
                .andShould().beStatic()
                .andShould().beFinal();
        rule.check(importedClasses);
    }


}
