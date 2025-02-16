package main_package.repository;

import java.util.ArrayList;
import main_package.model.Book;
import main_package.model.BookData;

public interface BookRepository {
  ArrayList<BookData> getAllBooksById (Long id);
  Long createBook(BookData book);
}
