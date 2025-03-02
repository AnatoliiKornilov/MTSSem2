package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CourseData", description = "Сущность курса")
public record CourseData(String name) {

}
