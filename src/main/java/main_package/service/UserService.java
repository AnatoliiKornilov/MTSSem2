package main_package.service;

import lombok.extern.slf4j.Slf4j;
import main_package.model.UserData;
import main_package.repository.UserRepository;
import main_package.request.UserCreateRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Long createUser(UserCreateRequest request) {
    log.info("Creating new user with username: {} {}", request.name(), request.surname());
    Long userId = userRepository.createUser(new UserData(request.name(), request.surname()));
    log.info("Created new user with id: {}", userId);
    return userId;
  }

  @Cacheable(value="user", key="#user")
  public UserData getUserById(Long userId) {
    log.info("Getting user by id: {}", userId);
    UserData user = userRepository.getUserDataById(userId);
    log.info("Found user: {} {}", user.name(), user.surname());
    return user;
  }
}
