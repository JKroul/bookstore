package jkroul.bookstore.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String authorName;

    @OneToMany(mappedBy = "author")
    private List<Book> Books;

    public Author() {
    }

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public List<Book> getBooks() {
        return Books;
    }
}