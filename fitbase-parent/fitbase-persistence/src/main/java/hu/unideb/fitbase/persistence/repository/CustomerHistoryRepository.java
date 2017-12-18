package hu.unideb.fitbase.persistence.repository;

import hu.unideb.fitbase.persistence.entity.CustomerHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Customer history repository.
 */
@Repository
public interface CustomerHistoryRepository extends JpaRepository<CustomerHistoryEntity, Long> {

    List<CustomerHistoryEntity> findByCustomerEntityId(@Param("id") long id);
    
    @Query("SELECT COUNT(c) FROM CustomerHistoryEntity c WHERE c.passBuyDate = CURRENT_DATE  AND c.gymEntity.id = :id")
    Double countPassesOnCurrentDateByGym(@Param("id") long id);
    
    @Query("SELECT SUM(c.passPrice) FROM CustomerHistoryEntity c WHERE c.passBuyDate = CURRENT_DATE  AND c.gymEntity.id = :id")
    Double sumCostOfPassesOnCurrentDateByGym(@Param("id") long id);
  
    @Query("SELECT COUNT(c.customerEntity.id) FROM CustomerHistoryEntity c WHERE c.passBuyDate = CURRENT_DATE AND c.gymEntity.id = :id")
    Double sumCustomersOnCurrentDateByGym(@Param("id") long id);
}
