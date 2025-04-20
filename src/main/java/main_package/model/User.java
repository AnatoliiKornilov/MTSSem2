package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import lombok.Data;

@Schema(name = "User", description = "Сущность пользователя с id")
@Entity
@Data
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private UserData fullName;

  @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.PERSIST})
  private ArrayList<BookData> books;

  @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.PERSIST})
  private UniversityData university;

  @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.PERSIST})
  private ArrayList<CourseData> courses;

  public User(Long id, UserData fullName) {
    this.id = id;
    this.fullName = fullName;
    this.books = new ArrayList<>();
    this.courses = new ArrayList<>();
    this.university = null;
  }
}
