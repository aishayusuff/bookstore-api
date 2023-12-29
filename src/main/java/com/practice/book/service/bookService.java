package com.practice.book.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.practice.book.model.Book;
import com.practice.book.repository.Repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {
    private final BookRepository repository;

    // @Autowired
    public BookService (BookRepository repository) {
        this.repository = repository;
    }


    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book with id: " + id + " Not found"));
    }

    public List<Book> getAllDiscountedBooks(double discountPercent) {
        List<Book> allBooks = getAllBooks();
        return allBooks.stream().map(book -> book.applyDiscount(discountPercent)).collect(Collectors.toList());

    }

    public Book createNewBook(Book newBook) {
        return repository.save(newBook);
    }

    public Book updateBookPrice(Long id, double updatedPrice) {
        Book bookToUpdate = getBookByID(id);

        bookToUpdate.setPrice(updatedPrice);
        return repository.save(bookToUpdate);
    }

    public Book updateBookStock(Long id, int updatedQuantityOfStock) {
        Book bookToUpdate = getBookByID(id);
        bookToUpdate.setQuantityOfStock(updatedQuantityOfStock);
        return repository.save(bookToUpdate);
    }



}
