package se.lexicon.jpalibraryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.jpalibraryapp.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {


    List<Book> findByIsbnIgnoreCase(String isbn);

    List<Book> findByTitleContains(String title);

    List<Book> findByMaxLoanDaysIsLessThan(int maxLoanDaysIsLessThan);



}