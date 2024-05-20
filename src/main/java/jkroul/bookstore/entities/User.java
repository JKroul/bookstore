package jkroul.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private long balance;

    private long points;

    public User() {
    }

    public User(String username, String password, long balance, long points) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.points = points;
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

    public void addBalance (long amount) {
        this.balance = this.balance + amount;
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

    public void subtractPoints (long amount) {
        this.points = this.points - amount;
    }

    public void createUser (String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.points = 0;
    }
}
