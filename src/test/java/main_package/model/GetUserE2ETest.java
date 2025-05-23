package main_package.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import main_package.response.UserGetResponse;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
@Slf4j
class GetUserE2ETest {

  private static final UserGetResponse correctUserResponse =
      new UserGetResponse("Анатолий", "Корнилов");

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @ClassRule
  public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13")
      .withInitScript("init.sql")
      .withDatabaseName("testdb")
      .withUsername("testuser")
      .withPassword("testpass");

  static {
    postgresContainer.start();
  }

  @Test
  public void testGetUser() {
    String jdbcUrl = postgresContainer.getJdbcUrl();
    log.info(jdbcUrl);
    String url = "http://localhost:" + port + "/api/user/1";
    ResponseEntity<UserGetResponse> response = restTemplate.getForEntity(url,
        UserGetResponse.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(correctUserResponse, response.getBody());
  }
}