package com.practice.book.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.book.model.Book;
import com.practice.book.model.BookDTO;
import com.practice.book.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/bookerly")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //To create books
    @PostMapping("/create-books")
    public ResponseEntity<BookDTO> createsBook(@RequestBody BookDTO newBook) {
        Book book = newBook.toEntity();
        Book createdBook = bookService.createNewBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(BookDTO.fromEntity(createdBook));
    }

    //To get all books
    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        List<BookDTO> allBookDTOs = allBooks.stream().map(book -> BookDTO.fromEntity(book)).collect(Collectors.toList());
        return ResponseEntity.ok(allBookDTOs);
    }
    

}
