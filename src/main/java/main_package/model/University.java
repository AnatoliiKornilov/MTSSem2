package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "University", description = "Сущность университета с id")
@Entity
@Getter
@Setter
@Table(name = "universities")
public class University {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long universityId;

  @Column(name="university_name")
  @NotNull
  private String universityName;

  @Column(name="location")
  @NotNull
  private String location;

  @Column(name="users")
  @NotNull
  @OneToMany(mappedBy="university")
  private Set<User> users;

  public University() {}

  public University(Long universityId, String universityName, String location) {
    this.universityId = universityId;
    this.universityName = universityName;
    this.location = location;
    this.users = new HashSet<>();
  }
}
