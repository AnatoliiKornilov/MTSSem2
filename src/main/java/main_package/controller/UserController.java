package main_package.controller;

import main_package.model.UserData;
import main_package.request.UserCreateRequest;
import main_package.response.UserGetResponse;
import main_package.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/")
  public ResponseEntity<Long> createUser(@RequestBody UserCreateRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserGetResponse> getUser(@PathVariable Long userId) {
    UserData user = userService.getUserById(userId);
    return ResponseEntity.status(HttpStatus.OK).body(new UserGetResponse(user.name(), user.surname()));
  }
}
