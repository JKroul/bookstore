package jkroul.bookstore.controllers;

import jkroul.bookstore.entities.Book;

import jkroul.bookstore.repositories.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("index")
    public String listBooks(Model model) {
        List<Book> Books = bookRepository.findAll();
        List<Book> recommendedBooks = bookRepository.findAll(PageRequest.of(0, 5)).getContent();

        System.out.println("Fetched books: " + Books);
        System.out.println("Fetched recommended books: " + recommendedBooks);

        model.addAttribute("books", Books);
        model.addAttribute("recommendedBooks", recommendedBooks);

        return "index";
    }
}