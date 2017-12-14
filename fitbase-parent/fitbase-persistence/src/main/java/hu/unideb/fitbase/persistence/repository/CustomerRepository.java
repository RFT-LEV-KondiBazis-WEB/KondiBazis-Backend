package hu.unideb.fitbase.persistence.repository;

import hu.unideb.fitbase.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Customer repository.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("SELECT c FROM CustomerEntity c WHERE c.email = :email")
    CustomerEntity findByEmail(@Param("email") String email);

    @Query("SELECT c FROM CustomerEntity c WHERE c.id = :id")
    CustomerEntity findById(@Param("id") long id);

    @Query("SELECT COUNT(c) FROM CustomerEntity c")
    Long countCustomers();

    @Query("SELECT c.email FROM CustomerEntity c")
    List<String> getAllEmail();

}
