package main_package.repository;

import java.util.ArrayList;
import main_package.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookRepository extends JpaRepository<Book, Long> {
  ArrayList<Book> getAllBooksById (Long id);
}
