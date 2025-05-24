package main_package.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

  @PersistenceUnit
  EntityManagerFactory emf;
}
