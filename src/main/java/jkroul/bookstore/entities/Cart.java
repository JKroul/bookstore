package jkroul.bookstore.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Book> booksInCart;

    @OneToOne (mappedBy = "user")
    private User user;

    @Column
    private int cartCost;

    public Cart() {
    }

    public Cart(List<Book> books, User user, int cartCost) {
        this.booksInCart = books;
        this.user = user;
        this.cartCost = cartCost;
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

    public int updateCartCost() {
        int total = 0;
        for (Book book : booksInCart) {
            total += book.getPrice();
        }
        this.cartCost = total;
        return total;
    }

}