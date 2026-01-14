package se.lexicon.jpalibraryapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private int id;

    @Setter
   @Column(length = 50,unique = true,nullable = false)
    private String username;

    @Setter
    @Column(length = 50,nullable = false)
    private String password;

    @Setter
    private LocalDate regDate;

    @OneToOne
    @JoinColumn(name= "details_id")
    private Details userDetails;


    public AppUser(String username, String password, LocalDate regDate, Details userDetails) {
        this.username = username;
        this.password = password;
        this.regDate = regDate;
        this.userDetails = userDetails;
    }
}
