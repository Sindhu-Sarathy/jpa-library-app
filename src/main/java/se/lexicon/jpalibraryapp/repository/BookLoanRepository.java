package se.lexicon.jpalibraryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.jpalibraryapp.entity.BookLoan;

public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {
}