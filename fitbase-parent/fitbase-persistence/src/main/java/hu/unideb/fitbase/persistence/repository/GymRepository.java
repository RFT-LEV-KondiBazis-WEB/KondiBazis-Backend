package hu.unideb.fitbase.persistence.repository;

import hu.unideb.fitbase.persistence.entity.GymEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Gym repository.
 */
@Repository
public interface GymRepository extends JpaRepository<GymEntity, Long>{
}
