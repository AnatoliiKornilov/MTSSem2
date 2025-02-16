package main_package.controller;

import java.util.List;
import java.util.stream.Collectors;
import main_package.request.BookCreateRequest;
import main_package.response.BookGetResponse;
import main_package.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<BookGetResponse>> getAllBooksById(@PathVariable Long userId) {
    return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooksById(userId).stream()
        .map(bookData -> new BookGetResponse(bookData.title(), bookData.author()))
        .collect(Collectors.toList()));
  }

  @PutMapping("/user/{userId}")
  public ResponseEntity<Void> addBookForUserById(
      @PathVariable Long userId, @RequestBody BookCreateRequest book) {
    bookService.createBook(book);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
