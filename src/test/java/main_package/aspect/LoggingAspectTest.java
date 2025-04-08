package main_package.aspect;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import main_package.request.UserCreateRequest;
import main_package.response.UserGetResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class LoggingAspectTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private LoggingAspect loggingAspect;

  @Test
  public void testUserController() {
    int value = loggingAspect.value;

    UserCreateRequest userRequest = new UserCreateRequest("Name", "Surname");
    ResponseEntity<Long> createResponse =
        restTemplate.postForEntity("/api/user", userRequest, Long.class);

    assertTrue(createResponse.getStatusCode().isSameCodeAs(HttpStatus.CREATED));
    Long userId = createResponse.getBody();
    assertNotNull(userId);

    assertEquals(2, loggingAspect.value - value);

    ResponseEntity<UserGetResponse> userResponse =
        restTemplate.getForEntity("/api/user/" + userId, UserGetResponse.class);
    assertTrue(userResponse.getStatusCode().is2xxSuccessful());

    assertEquals(4, loggingAspect.value - value);
  }
}
