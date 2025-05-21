package main_package.service;

import lombok.extern.slf4j.Slf4j;
import main_package.exception.UserNotFoundException;
import main_package.model.User;
import main_package.repository.UserRepository;
import main_package.request.UserCreateRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public Long createUser(UserCreateRequest request) {
    log.info("Creating new user with username: {} {}", request.name(), request.surname());
    User user = userRepository.save(new User(null, request.name(), request.surname()));
    log.info("Created new user");
    return user.getUserId();
  }

  @Cacheable(value="user", key="#user")
  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  public User getUserById(Long userId) {
    log.info("Getting user by id: {}", userId);
    User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    log.info("Found user: {} {}", user.getUserName(), user.getUserSurname());
    return user;
  }
}
