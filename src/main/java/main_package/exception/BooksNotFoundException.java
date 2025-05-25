package main_package.exception;

public class BooksNotFoundException extends RuntimeException {

  public BooksNotFoundException() {
    super("Список книг не найден");
  }
}
