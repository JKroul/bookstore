package jkroul.bookstore.repositories;

import jkroul.bookstore.entities.book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<book, Long> {
}