```mermaid
classDiagram
    class Details {
        - int id
        - String email
        - String name
        - LocalDate birthDate
        // Constructor() and other methods as needed
        
    }

    class AppUser {
        - int id
        - String username
        - String password
        - LocalDate regDate
        - Details userDetails
        // Constructor() and other methods as needed
    }

    AppUser "1" --> "1" Details : OneToOne
```