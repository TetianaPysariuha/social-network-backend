package org.finalproject.repository;




import org.finalproject.entity.BaseEntity;
import org.finalproject.exception.DataNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryInterface<E extends BaseEntity> extends JpaRepository<E, Long> {
    default E findEntityById(Long id) {
        Optional<E> entityOptional = findById(id);
        if (entityOptional.isEmpty()) {
            String msg = String.format("An error occurred while trying to find entity with id %d. ", id);
            throw new DataNotFoundException(msg);
        }
        return entityOptional.get();
    }
}