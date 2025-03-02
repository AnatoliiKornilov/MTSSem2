package main_package.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Book", description = "Сущность книги с id")
public record Book (Long id, BookData bookData) {}
