package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(name = "University", description = "Сущность университета с id")
@Entity
@Data
@Table(name = "universities")
public class University {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private UniversityData universityData;

  public University(Long id, UniversityData universityData) {
    this.id = id;
    this.universityData = universityData;
  }
}
