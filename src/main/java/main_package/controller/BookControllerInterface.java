package main_package.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import main_package.exception.BooksNotFoundException;
import main_package.request.BookCreateRequest;
import main_package.response.BookGetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Book API", description = "Управление книгами")
public interface BookControllerInterface {

  @Operation(summary = "Получить список книг по id")
  @ApiResponse(responseCode = "200", description = "Список книг получен")
  @ApiResponse(responseCode = "404", description = "Книга не найдена",
      content = @Content(schema = @Schema(implementation = BooksNotFoundException.class)))
  @GetMapping("{userId}/book")
  ResponseEntity<List<BookGetResponse>> getAllBooks(@Parameter(description = "ID пользователя")
  @PathVariable Long userId);

  @Operation(summary = "Добавить книгу пользователю по его id")
  @ApiResponse(responseCode = "201", description = "Книга добавлена пользователю")
  @PutMapping("{userId}/book")
  ResponseEntity<Void> addBookForUserById(@Parameter(description = "ID пользователя")
  @PathVariable Long userId, @RequestBody BookCreateRequest book);

  @Operation(summary = "Обновить книгу по id для пользователя по id")
  @ApiResponse(responseCode = "200", description = "Книга успешно обновлена")
  @ApiResponse(responseCode = "404", description = "Книга не найдена",
      content = @Content(schema = @Schema(implementation = BooksNotFoundException.class)))
  @PatchMapping("/{userId}/book/{bookId}")
  ResponseEntity<Void> updateBook(@Parameter(description = "ID пользователя") @PathVariable Long userId,
      @Parameter(description = "ID книги") @PathVariable Long bookId, @RequestBody BookCreateRequest book);

  @Operation(summary = "Удалить книгу по id пользователя по id")
  @ApiResponse(responseCode = "200", description = "Книга успешно удалена")
  @ApiResponse(responseCode = "404", description = "Книга не найдена",
      content = @Content(schema = @Schema(implementation = BooksNotFoundException.class)))
  @DeleteMapping("/{userId}/book/{bookId}")
  ResponseEntity<Void> deleteBook(@Parameter(description = "ID пользователя") @PathVariable Long userId,
      @Parameter(description = "ID книги") @PathVariable Long bookId);
}
