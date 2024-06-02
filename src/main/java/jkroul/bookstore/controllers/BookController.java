package jkroul.bookstore.controllers;

import jkroul.bookstore.entities.Author;
import jkroul.bookstore.entities.Book;
import jkroul.bookstore.entities.Cart;
import jkroul.bookstore.entities.User;
import jkroul.bookstore.repositories.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final User user;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.user = new User(); // Initialize the user. In a real application, retrieve the current logged-in user.
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> Books = bookRepository.findAll();
        List<Book> recommendedBooks = bookRepository.findAll(PageRequest.of(0, 5)).getContent();

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

    @PostMapping("/add-to-cart")
    @ResponseBody
    public String addToCart(@RequestParam("bookId") Book bookId) {
        user.getCart().addBook(bookId);

        return "Book added to cart";
    }
}


