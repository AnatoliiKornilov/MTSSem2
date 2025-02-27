package main_package.exception;

public class UniversityNotFoundException extends RuntimeException {
  public UniversityNotFoundException(String message) {
    super(message);
  }
}
