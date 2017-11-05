package hu.unideb.fitbase.persistence.repository;

import hu.unideb.fitbase.persistence.entity.PassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Pass repository.
 */
@Repository
public interface PassRepository extends JpaRepository<PassEntity, Long>{
}
