package se.lexicon.jpalibraryapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@NoArgsConstructor
@ToString
@Entity

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Setter
    @Column(length = 120,nullable = false)
    private String firstName;

    @Setter
    @Column(length = 120,nullable = false)
    private String lastName;

    @Setter
    @ManyToMany
    private Set<Book> writtenBooks;

    public Author(String firstName, String lastName, Set<Book> writtenBooks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.writtenBooks = writtenBooks;
    }

    public void addBook(Book book){
        if(writtenBooks.add(book)){
            book.getAuthors().add(this);
        }
    }

    public void removeBook(Book book){
        if(writtenBooks.remove(book)){
            book.getAuthors().remove(this);
        }
    }


}