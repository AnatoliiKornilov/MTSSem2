package main_package.repository;

import java.util.ArrayList;
import main_package.model.CourseData;

public interface CourseRepository {
  ArrayList<CourseData> getAllCoursesById (Long id);
  Long createCourse (CourseData course);
  void updateCourse (Long userId, Long courseId, CourseData course);
  void deleteCourse (Long userId, Long courseId);
}
