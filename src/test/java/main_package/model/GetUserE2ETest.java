package main_package.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import main_package.response.UserGetResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetUserE2ETest {

  private static final UserGetResponse correctUserResponse =
      new UserGetResponse("Анатолий", "Корнилов");

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testGetUser() {
    String url = "http://localhost:" + port + "/api/user/1";
    ResponseEntity<UserGetResponse> response = restTemplate.getForEntity(url, UserGetResponse.class, String.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(correctUserResponse, response.getBody());
  }
}