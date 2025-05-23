package main_package.repository;

import main_package.model.UserData;

public interface UserRepository {

  UserData getUserDataById (Long id);
  Long createUser (UserData fullName);

}
