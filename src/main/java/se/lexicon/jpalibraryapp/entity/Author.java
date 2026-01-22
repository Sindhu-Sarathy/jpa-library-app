package se.lexicon.jpalibraryapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@NoArgsConstructor
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
    @Column(nullable = false)
    private Set<Book> writtenBooks;
}