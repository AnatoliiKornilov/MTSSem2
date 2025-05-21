package main_package;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("main_package.repository")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /*@Bean
  public FlywayMigrationStrategy repairFlyway() {
    return flyway -> {
      flyway.repair();
      flyway.migrate();
    };
  }*/
}