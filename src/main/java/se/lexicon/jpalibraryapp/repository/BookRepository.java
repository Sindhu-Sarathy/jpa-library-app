package se.lexicon.jpalibraryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.jpalibraryapp.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {


}