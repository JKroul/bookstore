package jkroul.bookstore;

import jkroul.bookstore.entities.book;
import jkroul.bookstore.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseTestRunner implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DatabaseTestRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<book> books = bookRepository.findAll();
        for (book book : books) {
            System.out.println("Book ID: " + book.getId());
            System.out.println("Book Name: " + book.getBookName());
            System.out.println("Author: " + book.getAuthor().getAuthorName());
            System.out.println("-------------------------------");
        }
    }
}