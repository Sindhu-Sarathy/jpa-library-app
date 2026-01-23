package se.lexicon.jpalibraryapp;

import com.sun.jdi.BooleanValue;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.jpalibraryapp.entity.AppUser;
import se.lexicon.jpalibraryapp.entity.Book;
import se.lexicon.jpalibraryapp.entity.Details;
import se.lexicon.jpalibraryapp.repository.AppUserRepository;
import se.lexicon.jpalibraryapp.repository.BookLoanRepository;
import se.lexicon.jpalibraryapp.repository.BookRepository;
import se.lexicon.jpalibraryapp.repository.DetailsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    AppUserRepository appUserRepository;
    DetailsRepository detailsRepository;
    BookRepository bookRepository;
    BookLoanRepository bookLoanRepository;

    @Autowired
    public AppCommandLineRunner(AppUserRepository appUserRepository, DetailsRepository detailsRepository,BookRepository bookRepository) {
        this.appUserRepository = appUserRepository;
        this.detailsRepository = detailsRepository;
        this.bookRepository=bookRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Details details = detailsRepository.save(new Details("test002@gmail.com","test",LocalDate.of(1991,02,16)));
        System.out.println("User Details added successfully!");

        appUserRepository.save(new AppUser("test123","1234",LocalDate.now(),details));
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



        /// BookLoan Repository

    }
}
