package main_package.exception;

public class CoursesNotFoundException extends RuntimeException {
  public CoursesNotFoundException(String message) {
    super(message);
  }
}
