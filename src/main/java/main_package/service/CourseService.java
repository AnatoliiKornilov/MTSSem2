package main_package.service;

import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import main_package.exception.CoursesNotFoundException;
import main_package.model.Course;
import main_package.repository.CourseRepository;
import main_package.request.CourseCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CourseService {
  
  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @Transactional
  public Long createCourse(CourseCreateRequest request) {
    log.info("Adding new course {}", request.name());
    Course course = courseRepository.save(new Course(request.name()));
    log.info("Created new course");
    return course.getCourseId();
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  public List<Course> getAllCoursesById(Long userId) {
    log.info("Getting all courses by id: {}", userId);
    List<Course> courses = courseRepository.findAllById(Collections.singleton(userId));
    log.info("Found courses:");
    for (int i = 0; i < courses.size(); i++) {
      log.info("{}; ", courses.get(i).getCourseName());
    }
    return courses;
  }

  @Transactional
  public void updateCourse(Long userId, Long courseId, CourseCreateRequest request) {
    log.info("Update course with id {} for user with id {}", courseId, userId);
    Course updatedCourse = courseRepository.save(new Course(courseId, request.name()));
    log.info("Course with id {} for user with id {} was updated", courseId, userId);
  }

  @Transactional
  public void deleteCourse(Long userId, Long courseId) {
    log.info("Delete course with id {} for user with id {}", courseId, userId);
    Course course = courseRepository.findById(courseId).orElseThrow(CoursesNotFoundException::new);
    courseRepository.delete(course);
    log.info("Course with id {} for user with id {} was deleted", courseId, userId);
  }
}
