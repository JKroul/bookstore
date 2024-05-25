package jkroul.bookstore.entities;

import jakarta.persistence.*;
import jkroul.bookstore.entities.Book;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Book> booksInCart;

    @OneToOne
    private User user;

    public Cart() {
    }

    public Cart(List<Book> books, User user) {
        this.booksInCart = books;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public List<Book> getBooks() {
        return booksInCart;
    }
    
    public void addBook(Book book) {
        this.booksInCart.add(book);
    }
    
    public void removeBook(Book book) {
        this.booksInCart.remove(book);
    }

    public void purchaseBooks() {
        for (Book book : booksInCart) {
            user.subtractBalance(book.getPrice());
            user.addPoints(book.getPrice()/10);
        }
        booksInCart.clear();
    }

    public int cartCost() {
        int cost = 0;
        for (Book book : booksInCart) {
            cost += book.getPrice();
        }
        return cost;
    }
}