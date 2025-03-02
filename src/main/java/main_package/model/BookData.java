package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "BookData", description = "Сущность книги")
public record BookData(String title, String author) {

}
