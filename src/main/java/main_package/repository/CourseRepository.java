package main_package.repository;

import java.util.ArrayList;
import main_package.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  ArrayList<Course> getAllCoursesById (Long id);
}
