package main_package.controller;

import java.util.List;
import java.util.stream.Collectors;
import main_package.request.CourseCreateRequest;
import main_package.response.CourseGetResponse;
import main_package.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<CourseGetResponse>> getAllCoursesById(@PathVariable Long userId) {
    return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCoursesById(userId).stream()
        .map(courseData -> new CourseGetResponse(courseData.name()))
        .collect(Collectors.toList()));
  }

  @PutMapping("/user/{userId}")
  public ResponseEntity<Void> addCourseForUserById(
      @PathVariable Long userId, @RequestBody CourseCreateRequest course) {
    courseService.createCourse(course);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
