package main_package.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import main_package.exception.CoursesNotFoundException;
import main_package.request.BookCreateRequest;
import main_package.request.CourseCreateRequest;
import main_package.response.CourseGetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Course API", description = "Управление курсами")
public interface CourseControllerInterface {

  @Operation(summary = "Получить список курсов по id пользователя")
  @ApiResponse(responseCode = "200", description = "Получен список курсов пользователя")
  @ApiResponse(responseCode = "404", description = "Курс не найден",
      content = @Content(schema = @Schema(implementation =  CoursesNotFoundException.class)))
  @GetMapping("/{userId}/course")
  ResponseEntity<List<CourseGetResponse>> getAllCoursesById(@Parameter(description = "ID пользователя")
  @PathVariable Long userId);

  @Operation(summary = "Добавить курс пользователю по его id")
  @ApiResponse(responseCode = "201", description = "Добавлен курс пользователю")
  @PutMapping("/{userId}/course")
  ResponseEntity<Void> addCourseForUserById( @Parameter(description = "ID пользователя")
  @PathVariable Long userId, @RequestBody CourseCreateRequest course);

  @Operation(summary = "Обновить курс по id пользователя по id")
  @ApiResponse(responseCode = "200", description = "Курс обновлён")
  @PatchMapping("/{userId}/course/{courseId}")
  ResponseEntity<Void> updateCourse(@Parameter(description = "ID пользователя") @PathVariable Long userId,
      @Parameter(description = "ID курса") @PathVariable Long courseId, @RequestBody CourseCreateRequest course);

  @Operation(summary = "Удалить курс по id пользователя по id")
  @ApiResponse(responseCode = "200", description = "Курс удалён")
  @DeleteMapping("/{userId}/course/{courseId}")
  ResponseEntity<Void> deleteCourse(@Parameter(description = "ID пользователя") @PathVariable Long userId,
      @Parameter(description = "ID курса") @PathVariable Long courseId);
}
