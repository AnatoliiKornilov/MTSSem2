package main_package.service;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main_package.exception.BooksNotFoundException;
import main_package.model.Book;
import main_package.repository.BookRepository;
import main_package.request.BookCreateRequest;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  @Transactional
  public Long createBook(BookCreateRequest request) {
    log.info("Adding new book {} by {}", request.title(), request.author());
    Book book = bookRepository.save(new Book(request.title(), request.author()));
    log.info("Created new book");
    return book.getBookId();
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  public List<Book> getAllBooks(Long userId) {
    log.info("Getting all books by id: {}", userId);
    List<Book> books = bookRepository.findAllById(Collections.singleton(userId));
    log.info("Found books:");
    for (int i = 0; i < books.size(); i++) {
      log.info("{} - {}; ", books.get(i).getTitle(), books.get(i).getAuthor());
    }
    return books;
  }

  @Async("taskExecutor")
  @Transactional
  public void updateBook(Long userId, Long bookId, BookCreateRequest request) {
    log.info("Update book with id {} for user with id {}", bookId, userId);
    Book updatedBook = bookRepository.save(new Book(bookId, request.title(), request.author()));
    log.info("Book updated successfully");
  }

  @Retryable(value = BooksNotFoundException.class, maxAttempts = 5, backoff = @Backoff(delay = 10_000))
  @Transactional
  public void deleteBook(Long userId, Long bookId) {
    log.info("Delete book with id {} for user with id {}", bookId, userId);
    Book book = bookRepository.findById(bookId).orElseThrow(BooksNotFoundException::new);
    bookRepository.delete(book);
    log.info("Book with id {} for user with id {} was deleted", bookId, userId);
  }
}
