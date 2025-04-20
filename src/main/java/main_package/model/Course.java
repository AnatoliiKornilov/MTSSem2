package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(name = "Course", description = "Сущность курса с id")
@Entity
@Data
@Table(name = "courses")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private CourseData courseData;

  public Course(Long id, CourseData courseData) {
    this.id = id;
    this.courseData = courseData;
  }
}
