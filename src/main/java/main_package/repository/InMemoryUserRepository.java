package main_package.repository;

import java.util.ArrayList;
import main_package.model.User;
import main_package.model.UserData;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {

  public UserData userData = new UserData("Анатолий", "Корнилов");

  @Override
  public UserData getUserDataById(Long id) {
    return userData;
  }

  @Override
  public Long createUser(UserData fullName) {
    User user = new User(78L, fullName, null, null, null);
    return 78L;
  }
}
