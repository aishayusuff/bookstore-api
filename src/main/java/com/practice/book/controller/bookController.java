package com.practice.book.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.book.model.Book;
import com.practice.book.model.BookDTO;
import com.practice.book.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create-books")
    public Book createsBook(@RequestBody BookDTO newBook) {
        Book book = newBook.toEntity();
        Book createdBook = bookService.createNewBook(book);
        return createdBook;
    }

}
