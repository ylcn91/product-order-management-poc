package com.doksanbir.productordermanagementpoc.shared;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Base entity providing common audit fields and lifecycle callbacks.
 * <p>
 * This abstract class defines common properties and behaviors for entities,
 * such as ID, createdAt, and updatedAt fields. It also provides lifecycle
 * callback methods to automatically set timestamps during entity persistence
 * and updates.
 * </p>
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * The primary key ID for the entity.
     * <p>
     * It is automatically generated using the {@link GenerationType#IDENTITY} strategy.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The timestamp when the entity was created.
     * <p>
     * This field is automatically set before persisting the entity. It is immutable
     * after creation, ensuring a consistent record of when the entity was initially created.
     * </p>
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when the entity was last updated.
     * <p>
     * This field is automatically updated whenever the entity is modified, providing
     * an audit trail for changes.
     * </p>
     */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Sets default values before persisting the entity.
     * <p>
     * This lifecycle callback is invoked by the JPA provider before the entity is persisted
     * for the first time. It sets the {@link #createdAt} and {@link #updatedAt} fields
     * to the current date and time, ensuring that the entity's creation timestamp is properly recorded.
     * </p>
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    /**
     * Updates the {@link #updatedAt} field before updating the entity.
     * <p>
     * This lifecycle callback is invoked by the JPA provider before the entity is updated.
     * It sets the {@link #updatedAt} field to the current date and time, ensuring that the
     * timestamp of the most recent update is recorded.
     * </p>
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
