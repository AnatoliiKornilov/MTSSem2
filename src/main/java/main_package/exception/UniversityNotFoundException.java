package main_package.exception;

public class UniversityNotFoundException extends RuntimeException {

  public UniversityNotFoundException() {
    super("Университет не найден");
  }
}
