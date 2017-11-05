package hu.unideb.fitbase.service.api.config;

import hu.unideb.fitbase.persistence.config.PersistenceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfiguration.class)
@ComponentScan("hu.unideb.fitbase.service.api")
public class ServiceConfiguration {
}