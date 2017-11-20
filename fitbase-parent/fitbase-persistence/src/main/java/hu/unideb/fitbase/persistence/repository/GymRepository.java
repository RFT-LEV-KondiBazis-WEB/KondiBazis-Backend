package hu.unideb.fitbase.persistence.repository;

import hu.unideb.fitbase.persistence.entity.GymEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Gym repository.
 */
@Repository
public interface GymRepository extends JpaRepository<GymEntity, Long>{
	
    /**
     * Find GYM by name.
     */
	@Query("SELECT g FROM GymEntity g WHERE g.name = :name")
	GymEntity findByName(@Param("name") String name);
}
