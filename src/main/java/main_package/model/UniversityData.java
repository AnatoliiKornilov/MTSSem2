package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UniversityData", description = "Сущность университета")
public record UniversityData(String name, String location) {
}
