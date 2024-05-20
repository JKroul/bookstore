package jkroul.bookstore.entities;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "book_name")
    private String bookName;

    @Column
    private long price;

    @Column
    private String genre;

    @ManyToOne
    private Author author;


    public Book() {
    }

    public Book(String bookName, Author author, long price, String genre) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
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