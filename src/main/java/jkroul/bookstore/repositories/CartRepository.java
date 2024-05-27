package jkroul.bookstore.repositories;

import jkroul.bookstore.entities.Book;
import jkroul.bookstore.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository<Book> extends JpaRepository<Book, Long> {
}