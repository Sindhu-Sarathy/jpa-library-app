package se.lexicon.jpalibraryapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity

public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Setter
    @Column(nullable = false)
    private LocalDate loanDate;

    @Setter
    @Column(nullable = false)
    private LocalDate dueDate;

    @Setter
    @Column(nullable = false)
    private boolean returned;

    @Setter
    @ManyToOne
    @JoinColumn(name="appuser_id")
    private AppUser borrower;

    @Setter
    @ManyToOne
    private Book book;
}