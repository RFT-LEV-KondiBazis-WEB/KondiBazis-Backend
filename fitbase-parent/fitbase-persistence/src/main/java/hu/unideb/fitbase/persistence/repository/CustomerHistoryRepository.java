package hu.unideb.fitbase.persistence.repository;

import hu.unideb.fitbase.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Customer history repository.
 */
@Repository
public interface CustomerHistoryRepository extends JpaRepository<CustomerEntity, Long> {
}
