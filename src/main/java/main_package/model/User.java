package main_package.model;

import static jakarta.persistence.FetchType.LAZY;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "User", description = "Сущность пользователя с id")
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(name="userName")
  @NotNull
  private String userName;

  @Column(name="userSurname")
  @NotNull
  private String userSurname;

  @Column(name="books")
  @NotNull
  @ManyToMany(fetch = LAZY)
  @JoinTable(
      name = "book_user",
      joinColumns = @JoinColumn(name = "userId"),
      inverseJoinColumns = @JoinColumn(name = "bookId")
  )
  private Set<Book> books;

  @Column(name="courses")
  @NotNull
  @ManyToMany(fetch = LAZY)
  @JoinTable(
      name = "course_user",
      joinColumns = @JoinColumn(name = "userId"),
      inverseJoinColumns = @JoinColumn(name = "courseId")
  )
  private Set<Course> courses;

  @NotNull
  @ManyToOne
  @JoinColumn(name="universityId", referencedColumnName = "universityId")
  private University university;

  public User() {}

  public User(Long userId, String userName, String userSurname) {
    this.userId = userId;
    this.userName = userName;
    this.userSurname = userSurname;
    this.books = new HashSet<>();
    this.courses = new HashSet<>();
    this.university = null;
  }
}
