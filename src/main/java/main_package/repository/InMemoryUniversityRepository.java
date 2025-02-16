package main_package.repository;

import main_package.model.University;
import main_package.model.UniversityData;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUniversityRepository implements UniversityRepository {

  public UniversityData university = new UniversityData("МФТИ", "Долгопа");

  @Override
  public UniversityData getUniversityById(Long id) {
    return university;
  }

  @Override
  public Long createUniversity(UniversityData universityData) {
    University university = new University(78L, universityData);
    return 78L;
  }
}
