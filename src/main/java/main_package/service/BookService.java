package main_package.service;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import main_package.exception.BooksNotFoundException;
import main_package.model.BookData;
import main_package.repository.BookRepository;
import main_package.request.BookCreateRequest;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Long createBook(BookCreateRequest request) {
    log.info("Adding new book {} by {}", request.title(), request.author());
    Long courseId = bookRepository.createBook(new BookData(request.title(), request.author()));
    log.info("Created new book with id: {}", courseId);
    return courseId;
  }

  public ArrayList<BookData> getAllBooksById(Long userId) {
    log.info("Getting all books by id: {}", userId);
    ArrayList<BookData> books = bookRepository.getAllBooksById(userId);
    log.info("Found books:");
    for (int i = 0; i < books.size(); i++) {
      log.info("{} - {}; ", books.get(i).title(), books.get(i).author());
    }
    return books;
  }

  @Async("taskExecutor")
  public void updateBook(Long userId, Long bookId, BookCreateRequest request) {
    log.info("Update book with id {} for user with id {}", bookId, userId);
    BookData bookData = new BookData(request.title(), request.author());
    bookRepository.updateBook(userId, bookId, bookData);
    log.info("Book with id {} for user with id {} was updated", bookId, userId);
  }

  public void createBookList(Long userId) {
    log.info("Create new booklist to user with id {}", userId);
    bookRepository.createBookList(userId);
    log.info("Booklist was successfully created");
  }

  // Удаление - долгий и сложный процесс, надо гарантировать его завершение
  @Retryable(value = BooksNotFoundException.class, maxAttempts = 5, backoff = @Backoff(delay = 10_000))
  public void deleteBook(Long userId, Long bookId) {
    log.info("Delete book with id {} for user with id {}", bookId, userId);
    bookRepository.deleteBook(userId, bookId);
    log.info("Book with id {} for user with id {} was deleted", bookId, userId);
  }
}
