package main_package.repository;

import java.util.ArrayList;
import main_package.model.BookData;

public interface BookRepository {
  ArrayList<BookData> getAllBooksById (Long id);
  Long createBook(BookData book);
  void updateBook(Long userId, Long bookId, BookData bookData);
  void createBookList(Long userId);
  void deleteBook(Long userId, Long bookId);
}
