package se.lexicon.jpalibraryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import se.lexicon.jpalibraryapp.entity.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {


    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);


     List<Author> findByFirstNameOrLastNameContainsIgnoreCase(String firstName, String lastName);

     List<Author> findByWrittenBooks_Id(int writtenBooksId);

     @Modifying
    @Query("UPDATE Author A SET A.firstName=?1,A.lastName=?2 WHERE A.id=?3")
    void updateName(String firstName,String lastName,int id);

    void deleteById(int id);
}