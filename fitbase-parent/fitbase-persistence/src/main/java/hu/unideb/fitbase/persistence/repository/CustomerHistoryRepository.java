package hu.unideb.fitbase.persistence.repository;

import hu.unideb.fitbase.persistence.entity.CustomerHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Customer history repository.
 */
@Repository
public interface CustomerHistoryRepository extends JpaRepository<CustomerHistoryEntity, Long> {

    List<CustomerHistoryEntity> findByCustomerEntityId(@Param("id") long id);

}
