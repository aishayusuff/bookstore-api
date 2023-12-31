package com.practice.book.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.book.exception.BookNotFoundException;
import com.practice.book.model.Book;
import com.practice.book.model.BookDTO;
import com.practice.book.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/bookerly/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //To create books
    @PostMapping()
    public ResponseEntity<BookDTO> createsBook(@RequestBody BookDTO newBook) {
        Book book = newBook.toEntity();
        Book createdBook = bookService.createNewBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(BookDTO.fromEntity(createdBook));
    }

    //To get all books
    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks() {
            List<Book> allBooks = bookService.getAllBooks();
            List<BookDTO> allBookDTOs = allBooks.stream().map(book -> BookDTO.fromEntity(book)).collect(Collectors.toList());

            if (allBooks.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(allBookDTOs);
    }
    
    //To get one book
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
       try {
        Book book = bookService.getBookByID(id);
        return ResponseEntity.ok(BookDTO.fromEntity(book));

      }catch (BookNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }
    }

    //To update the price or quantity of stock of a book
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO updatedBookDTO) {
        try {
            bookService.getBookByID(id);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Book updatedBook = updatedBookDTO.toEntity();
        Book updatedBookEntity = null;
        boolean isPriceUpdated = false;
        boolean isStockUpdated = false;

        if (updatedBook.getPrice() != 0.0) {
            double updatedBookPrice = updatedBook.getPrice(); 
            updatedBookEntity = bookService.updateBookPrice(id, updatedBookPrice);
            isPriceUpdated = true;
        }
        if (updatedBook.getQuantityOfStock() != -1) {
            int updatedBookStock = updatedBook.getQuantityOfStock(); 
            updatedBookEntity = bookService.updateBookStock(id, updatedBookStock);
           isStockUpdated = true;
        }

        if (isPriceUpdated || isStockUpdated) {
            return ResponseEntity.ok(BookDTO.fromEntity(updatedBookEntity));
        }
 
        return ResponseEntity.badRequest().build();
    }

    //To delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        try {
            bookService.deleteBookByID(id);
            return ResponseEntity.ok("Book has been deleted successfully!");
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID: " + id + " not found!");
        }
    }
}
