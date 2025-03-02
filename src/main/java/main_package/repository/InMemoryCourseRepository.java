package main_package.repository;

import java.util.ArrayList;
import main_package.exception.CoursesNotFoundException;
import main_package.model.Course;
import main_package.model.CourseData;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCourseRepository implements CourseRepository {

  CourseData course1 = new CourseData("Жёсткая бэкенд-разработка на джава");
  CourseData course2 = new CourseData("Кокающий анализ");
  CourseData course3 = new CourseData("Средневековые титулы: Графы");
  ArrayList<CourseData> allCourses = new ArrayList<>();

  @Override
  public ArrayList<CourseData> getAllCoursesById(Long id) {
    allCourses.add(course1);
    allCourses.add(course2);
    allCourses.add(course3);
    return allCourses;
  }

  @Override
  public Long createCourse(CourseData courseData) {
    Course course = new Course(78L, courseData);
    return 78L;
  }

  @Override
  public void updateCourse(Long userId, Long courseId, CourseData courseData) {
    if (courseId == 1) {
      course1 = courseData;
    } else if (courseId == 2) {
      course2 = courseData;
    } else if (courseId == 3) {
      course3 = courseData;
    } else {
      throw new CoursesNotFoundException();
    }
  }

  @Override
  public void deleteCourse(Long userId, Long courseId) {
    if (courseId == 1) {
      course1 = null;
    } else if (courseId == 2) {
      course2 = null;
    } else if (courseId == 3) {
      course3 = null;
    } else {
      throw new CoursesNotFoundException();
    }
  }
}
