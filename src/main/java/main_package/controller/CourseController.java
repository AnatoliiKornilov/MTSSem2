package main_package.controller;

import io.github.resilience4j.ratelimiter.RateLimiter;
import java.util.List;
import java.util.stream.Collectors;
import main_package.request.CourseCreateRequest;
import main_package.response.CourseGetResponse;
import main_package.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController implements CourseControllerInterface {

  private final CourseService courseService;
  private final RateLimiter rateLimiter = RateLimiter.ofDefaults("UniversityControllerRateLimiter");

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @Override
  public ResponseEntity<List<CourseGetResponse>> getAllCoursesById(Long userId) {
    return rateLimiter.executeSupplier(() -> {
    return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCoursesById(userId).stream()
        .map(courseData -> new CourseGetResponse(courseData.name()))
        .collect(Collectors.toList()));
    });
  }

  @Override
  public ResponseEntity<Void> addCourseForUserById(Long userId, CourseCreateRequest course) {
    return rateLimiter.executeSupplier(() -> {
    courseService.createCourse(course);
    return ResponseEntity.status(HttpStatus.CREATED).build();
    });
  }

  @Override
  public ResponseEntity<Void> updateCourse(Long userId, Long courseId, CourseCreateRequest course) {
    return rateLimiter.executeSupplier(() -> {
    courseService.updateCourse(userId, courseId, course);
    return ResponseEntity.status(HttpStatus.CREATED).build();
    });
  }

  @Override
  public ResponseEntity<Void> deleteCourse(Long userId, Long courseId) {
    return rateLimiter.executeSupplier(() -> {
    courseService.deleteCourse(userId, courseId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
    });
  }
}
