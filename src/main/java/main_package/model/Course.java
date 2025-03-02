package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Course", description = "Сущность курса с id")
public record Course(Long id, CourseData courseData) {

}
