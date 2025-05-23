package main_package.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import main_package.model.UserData;
import main_package.request.UserCreateRequest;
import main_package.response.UserGetResponse;
import main_package.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController implements UserControllerInterface {
  private final UserService userService;
  private final CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults(
      "UserControllerCircuitBreaker");

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Override
  public ResponseEntity<Long> createUser(UserCreateRequest request) {
    return circuitBreaker.executeSupplier(() -> {
      Long userId = userService.createUser(request);
      System.out.println(userId);
      log.info(userId + "!!!");
      return ResponseEntity.status(HttpStatus.CREATED).body(userId);
    });
  }

  @Override
  public ResponseEntity<UserGetResponse> getUser(Long userId) {
    return circuitBreaker.executeSupplier(() -> {
      UserData user = userService.getUserById(userId);
      return ResponseEntity.ok(new UserGetResponse(user.name(), user.surname()));
    });
  }
}
