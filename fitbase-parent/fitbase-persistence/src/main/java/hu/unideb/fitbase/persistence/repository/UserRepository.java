package hu.unideb.fitbase.persistence.repository;

import hu.unideb.fitbase.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u where u.username = :username")
    UserEntity findByUsername(@Param("username") String username);

    /**
     * Find user by email.
     */
    UserEntity findByEmail(@Param("email") String email);

    @Query("select count(u) > 0 from UserEntity u")
    boolean anyExists();
}
