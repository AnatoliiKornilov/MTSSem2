package main_package.repository;

import java.util.ArrayList;
import main_package.exception.BooksNotFoundException;
import main_package.model.Book;
import main_package.model.BookData;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookRepository implements BookRepository {

  BookData book1 = new BookData("Жёсткая бэкенд-разработка на джава", "Дмитрий Бобряков");
  BookData book2 = new BookData("Как вскрыть черепную коробку при помощи интегралов", "Райан Гослинг");
  BookData book3 = new BookData("Пушим матрицу на 35 ранг", "Бравлер Тик");
  ArrayList<BookData> allBooks = new ArrayList<>();

  @Override
  public ArrayList<BookData> getAllBooksById(Long id) {
    allBooks.add(book1);
    allBooks.add(book2);
    allBooks.add(book3);
    return allBooks;
  }

  @Override
  public Long createBook(BookData bookData) {
    Book book = new Book(78L, bookData);
    return 78L;
  }

  @Override
  public void updateBook(Long userId, Long bookId, BookData bookData) {
    if (bookId == 1L) {
      book1 = bookData;
    } else if (bookId == 2) {
      book2 = bookData;
    } else if (bookId == 3) {
      book3 = bookData;
    } else {
      throw new BooksNotFoundException();
    }
  }

  @Override
  public void createBookList(Long userId) {
    ArrayList<BookData> books = new ArrayList<>();
  }

  @Override
  public void deleteBook(Long userId, Long bookId) {
    if (bookId == 1L) {
      book1 = null;
    } else if (bookId == 2) {
      book2 = null;
    } else if (bookId == 3) {
      book3 = null;
    } else {
      throw new BooksNotFoundException();
    }
  }
}
