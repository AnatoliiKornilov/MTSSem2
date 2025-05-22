package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Course", description = "Сущность курса с id")
@Entity
@Getter
@Setter
@Table(name = "courses")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long courseId;

  @Column(name="courseName")
  @NotNull
  private String courseName;

  @Column(name="users")
  @NotNull
  @ManyToMany(mappedBy="courses")
  private Set<User> users = new HashSet<>();

  public Course() {}

  public Course(String courseName) {
    this.courseName = courseName;
  }

  public Course(Long courseId, String courseName) {
    this.courseId = courseId;
    this.courseName = courseName;
  }
}
