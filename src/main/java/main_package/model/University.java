package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "University", description = "Сущность университета с id")
public record University(Long id, UniversityData universityData) {

}
