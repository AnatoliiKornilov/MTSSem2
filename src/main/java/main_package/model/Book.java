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

@Schema(name = "Book", description = "Сущность книги с id")
@Entity
@Getter
@Setter
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookId;

  @Column(name="author")
  @NotNull
  private String author;

  @Column(name="title")
  @NotNull
  private String title;

  @Column(name="users")
  @NotNull
  @ManyToMany(mappedBy="books")
  private Set<User> users = new HashSet<>();

  public Book() {}

  public Book(String author, String title) {
    this.author = author;
    this.title = title;
  }

  public Book(Long bookId, String author, String title) {
    this.bookId = bookId;
    this.author = author;
    this.title = title;
  }
}
