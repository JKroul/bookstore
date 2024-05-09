package jkroul.bookstore.entities;

import jakarta.persistence.*;

@Entity
public class book {

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
    private author author;


    public book() {
    }

    public book(String bookName, author author) {
        this.book_name = bookName;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getBookName() {
        return book_name;
    }

    public author getAuthor() {
        return author;
    }
}