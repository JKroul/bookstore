package jkroul.bookstore.repositories;

import jkroul.bookstore.entities.author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<author, Long> {
}
