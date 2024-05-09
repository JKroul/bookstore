package jkroul.bookstore.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String authorName;

    @OneToMany(mappedBy = "author")
    private List<book> books;

    public author() {
    }

    public author(String authorName) {
        this.authorName = authorName;
    }

    public long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public List<book> getBooks() {
        return books;
    }
}