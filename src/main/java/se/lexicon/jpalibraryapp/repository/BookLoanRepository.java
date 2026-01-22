package se.lexicon.jpalibraryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import se.lexicon.jpalibraryapp.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {


   List<BookLoan> findByBorrower_Id(int borrowerId);

   List<BookLoan> findByBook_Id(int bookId);

    List<BookLoan> findByReturnedFalse();

    List<BookLoan> findByDueDateIsAfter(LocalDate dueDateAfter);

//    @Query("SELECT b FROM BookLoan b WHERE b.dueDate > CURRENT_TIMESTAMP")
//    List<BookLoan> findByIsOverdue();


    default List<BookLoan> findByIsOverdue(){
        return findByDueDateIsAfter(LocalDate.now());
    }

    List<BookLoan> findByLoanDateBetween(LocalDate loanDateAfter, LocalDate loanDateBefore);


    @Modifying
    @Query("UPDATE BookLoan bl SET bl.returned=true WHERE bl.id=:id")
    void  returnBook(int id);





}