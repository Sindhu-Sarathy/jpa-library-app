package se.lexicon.jpalibraryapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Setter
    @Column(length = 100,nullable = false)
    private String isbn;

    @Setter
    @Column(length = 100,nullable = false)
    private String title;

    @Setter
    private int maxLoanDays;

    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }
}