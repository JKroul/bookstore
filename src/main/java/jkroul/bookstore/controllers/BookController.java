package jkroul.bookstore.controllers;

import jkroul.bookstore.entities.Author;
import jkroul.bookstore.entities.Book;
import jkroul.bookstore.repositories.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> Books = bookRepository.findAll();
        List<Book> recommendedBooks = bookRepository.findAll(PageRequest.of(0, 5)).getContent();

        /* Print out the books in recommendedBooks
        for (Book book : recommendedBooks) {
            System.out.println("Book ID: " + book.getId());
            System.out.println("Book Name: " + book.getBookName());
            System.out.println("Author: " + book.getAuthor().getAuthorName());
            System.out.println("-------------------------------");
        }*/

        model.addAttribute("books", Arrays.asList(
new Book("The Great Gatsby", new Author(), 10, "Fiction"),
new Book("To Kill a Mockingbird", new Author(), 15, "Fiction"),
new Book("1984", new Author(), 12, "Fiction"),
new Book("Pride and Prejudice", new Author(), 8, "Fiction"),
new Book("The Catcher in the Rye", new Author(), 11, "Fiction"),
new Book("The Hobbit", new Author(), 14, "Fantasy"),
new Book("The Lord of the Rings", new Author(), 20, "Fantasy")
        ));
        model.addAttribute("recommendedBooks", recommendedBooks);

        return "index";
    }
}


