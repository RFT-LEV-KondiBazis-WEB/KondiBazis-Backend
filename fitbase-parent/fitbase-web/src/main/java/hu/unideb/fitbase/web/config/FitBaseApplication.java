package hu.unideb.fitbase.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebConfiguration.class)
public class FitBaseApplication extends SpringBootServletInitializer {

  /**
   * Main method.
   */
  public static void main(final String[] args) {
    SpringApplication.run(FitBaseApplication.class, args);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
    return application.sources(FitBaseApplication.class);
  }
}
