package com.doksanbir.productordermanagementpoc.application.port.out;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Base repository port interface providing common methods.
 *
 * @param <T>  the entity type
 * @param <ID> the identifier type
 */
public interface BaseRepositoryPort<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    void deleteById(ID id);
    List<T> findAll();
    List<T> findAll(Specification<T> specification);
}
