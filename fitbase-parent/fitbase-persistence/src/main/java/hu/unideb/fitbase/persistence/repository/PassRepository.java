package hu.unideb.fitbase.persistence.repository;

import hu.unideb.fitbase.persistence.entity.PassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Pass repository.
 */
@Repository
public interface PassRepository extends JpaRepository<PassEntity, Long>{

    @Query("SELECT u FROM PassEntity u where u.id = :id")
    PassEntity findById(@Param("id") Long id);

    @Query("SELECT g FROM PassEntity g WHERE g.name = :name")
    PassEntity findByName(@Param("name") String name);

    List<PassEntity> findByGymEntitiesId(@Param("id") long id);
}
