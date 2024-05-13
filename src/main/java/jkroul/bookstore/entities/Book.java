package jkroul.bookstore.entities;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String book_name;

    @Column
    private long price;

    @Column
    private String genre;

    @ManyToOne
    private Author author;


    public Book() {
    }

    public Book(String bookName, Author author) {
        this.book_name = bookName;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getBookName() {
        return book_name;
    }

    public Author getAuthor() {
        return author;
    }

    public long getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }
}