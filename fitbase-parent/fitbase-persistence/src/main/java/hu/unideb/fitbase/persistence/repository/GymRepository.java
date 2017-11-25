package hu.unideb.fitbase.persistence.repository;

import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

	@Query("SELECT g FROM GymEntity g WHERE g.id = :id")
	GymEntity findById(@Param("id") long id);

}
