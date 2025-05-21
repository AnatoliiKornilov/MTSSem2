package main_package.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import main_package.model.University;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static jakarta.transaction.Transactional.TxType.NOT_SUPPORTED;

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
public class UniversityRepositoryTest {

  @Container
  @ServiceConnection
  public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:15-alpine");

  @Autowired
  UniversityRepository universityRepository;

  @Test
  void create() {
    University testUniversity = universityRepository.save(new University(null, "MIPT", "Dolgopa"));
    University responseUniversity = universityRepository.findById(testUniversity.getUniversityId()).orElseThrow();
    assertEquals(testUniversity, responseUniversity);
  }

  @Test
  void getById() {
    Optional<University> responseUniversity = universityRepository.findById(1L);
    assertTrue(responseUniversity.isPresent());
  }
}
