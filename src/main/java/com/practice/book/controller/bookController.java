package com.practice.book.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.book.model.Book;
import com.practice.book.model.BookDTO;
import com.practice.book.service.BookService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/bookerly")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create-books")
    public ResponseEntity<Book> createsBook(@RequestBody BookDTO newBook) {
        Book book = newBook.toEntity();
        Book createdBook = bookService.createNewBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

}
