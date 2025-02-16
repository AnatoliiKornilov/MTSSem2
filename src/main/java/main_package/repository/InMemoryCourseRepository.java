package main_package.repository;

import java.util.ArrayList;
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
}
