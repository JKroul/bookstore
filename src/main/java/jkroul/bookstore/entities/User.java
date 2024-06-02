package jkroul.bookstore.entities;

import jakarta.persistence.*;


@Entity(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column
    private String username;

@Column
    private String password;

@Column
    private long balance;

@Column
    private long points;

@OneToOne(mappedBy = "user")
private Cart cart;

    public User() {
    }

    public User(String username) {
    }

    public User(Long Id, String username, String password, long balance, long points, Cart cart) {
        this.id = Id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.points = points;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getBalance() {
        return balance;
    }

    public void addBalance (Double amount) {
        this.balance = (long) (this.balance + amount);
    }

    public void subtractBalance (long amount) {
        this.balance = this.balance - amount;
    }

    public long getPoints() {
        return points;
    }

    public void addPoints (long amount) {
        this.points = this.points + amount;
    }

    public void subtractPoints (Double amount) {
        this.points = (long) (this.points - amount);
    }

    public Cart getCart() {
        return cart;
    }

    public void createUser (String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.points = 0;
    }
}
