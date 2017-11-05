package hu.unideb.fitbase.persistence.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class for persistence module.
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "hu.unideb.fitbase.persistence.repository")
@EntityScan(basePackages = "hu.unideb.fitbase.persistence.entity")
public class PersistenceConfiguration {

}
