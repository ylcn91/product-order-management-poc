# Product Order Management System - Architectural Integrity with ArchUnit

This project demonstrates the effective use of **ArchUnit** in defining and enforcing architectural rules for a product order management system. It follows **Hexagonal Architecture** (Ports and Adapters) principles and emphasizes clean layering, loose coupling, and the proper separation of concerns between domain, application, and infrastructure layers.

### **1. Objective**

The primary goal of this project is to demonstrate how **ArchUnit** can be used to maintain architectural integrity while handling complex business functionalities in a real-world application. The codebase enforces architectural constraints and prevents violations, ensuring adherence to the design principles of **Hexagonal Architecture** throughout the development lifecycle.

## **2. Key Features**

- **Hexagonal Architecture**: The project strictly adheres to the **Ports and Adapters** architecture, separating business logic (domain layer) from infrastructure concerns (persistence and controllers).
- **ArchUnit Testing**: Architectural rules are tested using **ArchUnit**, ensuring correct layering, dependency management, and annotation usage across the codebase.
- **Spring Boot**: The project is built with **Spring Boot**, leveraging dependency injection, Spring Data JPA, and RESTful web services.
- **Business Functionality**: Includes use cases for product and order management (CRUD operations, stock adjustments, order processing strategies).
- **Domain-Driven Design (DDD)**: The system models core business entities such as `Order` and `Product`, which are encapsulated within the domain layer.
- **Strategy Pattern**: Implements a strategy pattern for processing orders based on their status (e.g., `Pending`, `Confirmed`, `Shipped`).
- **Specification Pattern**: Uses specifications to handle complex filtering and querying logic, ensuring flexible and maintainable business rules.

## **3. Technologies and Frameworks**

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL** (for persistence)
- **ArchUnit** (for architectural tests)
- **Lombok** (for reducing boilerplate code)

## **4. Project Structure**

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/doksanbir/productordermanagementpoc/
│   │       ├── application/
│   │       │   ├── port/
│   │       │   ├── service/
│   │       │   └── specification/
│   │       ├── domain/
│   │       ├── infrastructure/
│   │       │   ├── adapter/
│   │       │   └── persistence/
│   │       ├── shared/
│   │       └── configuration/
│   └── resources/
├── test/
│   ├── java/
│   │   └── com/doksanbir/productordermanagementpoc/
│   │       └── architecture/
└── README.md
```

### **5. Architectural Integrity with ArchUnit**

The project defines several **ArchUnit** test cases to ensure the architecture stays clean and maintains its intended design. Some key rules include:

- **Layering Rules**:
    - **Domain Layer** should not depend on application or infrastructure layers.
    - **Application Layer** should only depend on domain layer and should not be accessed by the infrastructure layer.
    - **Infrastructure Layer** should depend on application ports but should not depend on domain entities directly.

- **Annotation Rules**:
    - All **service classes** must be annotated with `@Service`.
    - All **controller classes** must be annotated with `@RestController`.
    - **Repositories** in the infrastructure layer must be annotated with `@Repository`.

- **Cyclic Dependency Checks**: Ensures that there are no cyclic dependencies between different slices of the system, preventing tight coupling.

- **Strategy and Specification Patterns**:
    - The system enforces a strategy pattern for order processing.
    - Specification pattern ensures flexible querying across the order and product domains.

## **6. Installation**

### **6.1. Prerequisites**

Ensure that the following dependencies are installed:

- Java 17 or higher
- Maven
- PostgreSQL

### **6.2. Setup**

1. Clone the repository:

   ```bash
   git clone https://github.com/your-repo/product-order-management-poc.git
   cd product-order-management-poc
   ```

2. Configure your PostgreSQL database in `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/product_order_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Build the project:

   ```bash
   mvn clean install
   ```

4. Run the project:

   ```bash
   mvn spring-boot:run
   ```

5. Access the REST API via `http://localhost:8080/api/orders` and `http://localhost:8080/api/products`.

## **7. Testing the Architecture with ArchUnit**

The project uses **ArchUnit** to enforce architectural rules through unit tests. To run the architectural tests:

```bash
mvn test
```

### **7.1. Key Tests**

- **AccessRulesTest**: Ensures proper access control between layers (e.g., no direct access from infrastructure to domain).
- **CyclicDependencyTest**: Detects cyclic dependencies between architectural layers.
- **AnnotationRulesTest**: Ensures that proper Spring annotations are applied to service and controller classes.

### **7.2. Example Test: Domain Layer Independence**

```java
@Test
void domainLayerShouldBeIndependent() {
    noClasses()
        .that().resideInAnyPackage(DOMAIN)
        .should().dependOnClassesThat().resideInAnyPackage(
            APPLICATION,
            INFRASTRUCTURE)
        .because("Domain layer should be independent of application and infrastructure layers")
        .check(importedClasses);
}
```

## **8. API Endpoints**

### **Order Management**

- **Create Order**: `POST /api/orders`
- **Get Order by ID**: `GET /api/orders/{id}`
- **List All Orders**: `GET /api/orders`
- **Advanced Search Orders**: `GET /api/orders/advanced-search`

### **Product Management**

- **Create Product**: `POST /api/products`
- **Get Product by ID**: `GET /api/products/{id}`
- **List All Products**: `GET /api/products`
- **Search Products**: `GET /api/products/search`

