package jkroul.bookstore.entities;

import jakarta.persistence.*;
import java.awt.print.Book;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany
    private List<Book> books;

    // getters and setters
}