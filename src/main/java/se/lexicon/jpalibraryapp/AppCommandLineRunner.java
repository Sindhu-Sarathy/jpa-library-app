package se.lexicon.jpalibraryapp;

import com.sun.jdi.BooleanValue;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.jpalibraryapp.entity.*;
import se.lexicon.jpalibraryapp.repository.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    AppUserRepository appUserRepository;
    DetailsRepository detailsRepository;
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    BookLoanRepository bookLoanRepository;

    @Autowired
    public AppCommandLineRunner(AppUserRepository appUserRepository, DetailsRepository detailsRepository,BookRepository bookRepository,AuthorRepository authorRepository,BookLoanRepository bookLoanRepository) {
        this.appUserRepository = appUserRepository;
        this.detailsRepository = detailsRepository;
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
        this.bookLoanRepository=bookLoanRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Details details = detailsRepository.save(new Details("test002@gmail.com","test",LocalDate.of(1991,02,16)));
        System.out.println("User Details added successfully!");

        AppUser user=  appUserRepository.save(new AppUser("test123","1234",LocalDate.now(),details));
        System.out.println("AppUser is added successfully!");

        ///AppUserRepository
        Optional<AppUser> appUser=appUserRepository.findByUsername("test123");
        appUser.ifPresent(System.out::println);

        LocalDate regDateAfter = LocalDate.of(0, 1, 1);
        LocalDate regDateBefore = LocalDate.of(2030, 1, 1);
        List<AppUser> appUsers=appUserRepository.findByRegDateBetween(regDateAfter,regDateBefore);
        System.out.println(appUsers);

        List<AppUser> appUser1=appUserRepository.findByUserDetails_Id(1);
        System.out.println(appUser1);

        Optional<AppUser> appUser2 = appUserRepository.findByEmailIgnoreCase("test002@gmail.com");
        appUser2.ifPresent(System.out::println);

        ///DetailsRepository
        Optional<Details> details1=detailsRepository.findByEmail("test002@gmail.com");
        details1.ifPresent(System.out::println);

        List<Details> detailsList = detailsRepository.findByNameContains("test");
        System.out.println(detailsList);

        List<Details> detailsList1 =detailsRepository.findByNameIgnoreCase("TEST");
        System.out.println(detailsList1);

        /// Book Repository

        bookRepository.save(new Book("123456789","5AM Club",15));
        System.out.println("Book has been saved successfully!");

        List<Book> bookByIsbn= bookRepository.findByIsbnIgnoreCase("123456789");
        System.out.println(bookByIsbn);

        List<Book> bookByTitle= bookRepository.findByTitleContains("5AM Club");
        System.out.println(bookByTitle);

        List<Book> maxDays=bookRepository.findByMaxLoanDaysIsLessThan(20);
        System.out.println(maxDays);

        /// Author Repository


        Book book1=new Book("234567891","Miracle Morning",15);
        bookRepository.save(book1);
        Book book2 =new Book("345678912","Atomic Habits",10);
        bookRepository.save(book2);
        Set<Book> books=new HashSet<>();
        Set<Book> books1=new HashSet<>();
        books1.add(book1);
        books.add(book2);

        Author author=new Author("Hal","Erod",books1);
        authorRepository.save(author);
        Author author1=new Author("James","Clear",books);
        authorRepository.save(author1);
        System.out.println("Author has been saved successfully");

        List<Author> fndByFirstName=authorRepository.findByFirstName("Hal");
        System.out.println(fndByFirstName);

        List<Author> fndByLastName=authorRepository.findByLastName("Clear");
        System.out.println(fndByLastName);

        List<Author> authorList=authorRepository.findByFirstNameOrLastNameContainsIgnoreCase("Hal","Clear");
        System.out.println(authorList);

        List<Author> authors=authorRepository.findByWrittenBooks_Id(3);
        System.out.println(authors);

        authorRepository.updateName("Robin","Sharma",1);
        System.out.println("The Author details get updated successfully!");

        authorRepository.deleteById(1);
        System.out.println("The author has been deleted successfully!");

        /// BookLoan Repository

        BookLoan bookLoan=new BookLoan(LocalDate.now(),false,user,book2);
        bookLoan.getDueDate();
        bookLoanRepository.save(bookLoan);
        System.out.println("The Book loan has been saved successfully!");

        List<BookLoan> bookLoans=bookLoanRepository.findByBorrower_Id(1);
        System.out.println(bookLoans);

        List<BookLoan> bookLoanList=bookLoanRepository.findByBook_Id(3);
        System.out.println(bookLoanList);

        List<BookLoan> loanList=bookLoanRepository.findByReturnedFalse();
        System.out.println(loanList);

        List<BookLoan> loanList1=bookLoanRepository.findByDueDateIsAfter(LocalDate.parse("2026-02-16"));
        System.out.println(loanList1);

        List<BookLoan> loans=bookLoanRepository.findByLoanDateBetween(LocalDate.parse("2026-02-16"),LocalDate.parse("2026-01-16"));
        System.out.println(loans);

        bookLoanRepository.returnBook(1);
        System.out.println("The Book has been returned");



    }
}
