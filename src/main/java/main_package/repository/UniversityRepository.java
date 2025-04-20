package main_package.repository;

import main_package.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UniversityRepository extends JpaRepository<University, Long> {
  University getUniversityById (Long id);
}
