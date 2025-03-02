package main_package.controller;

import java.util.List;
import java.util.stream.Collectors;
import main_package.request.BookCreateRequest;
import main_package.response.BookGetResponse;
import main_package.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class BookController implements BookControllerInterface {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @Override
  public ResponseEntity<List<BookGetResponse>> getAllBooksById(Long userId) {
    return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooksById(userId).stream()
        .map(bookData -> new BookGetResponse(bookData.title(), bookData.author()))
        .collect(Collectors.toList()));
  }

  @Override
  public ResponseEntity<Void> addBookForUserById(Long userId, BookCreateRequest book) {
    bookService.createBook(book);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> updateBook(Long userId, Long bookId, BookCreateRequest request) {
    bookService.updateBook(userId, bookId, request);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  public ResponseEntity<Void> createBookList(Long userId) {
    bookService.createBookList(userId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  public ResponseEntity<Void> deleteBook(Long userId, Long bookId) {
    bookService.deleteBook(userId, bookId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
