package main_package.repository;

import main_package.model.University;
import main_package.model.UniversityData;
import main_package.model.UserData;

public interface UniversityRepository {
  UniversityData getUniversityById (Long id);
  Long createUniversity (UniversityData university);
}
