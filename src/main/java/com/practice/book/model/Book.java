package com.practice.book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private double price;
    private int quantityOfStock;

    //Default constructor
    public Book() {

    }

    //Parameterised construtor
    public Book(String newTitle, String newAuthor, double newPrice, int newQuantityOfStock) {
        this.title = newTitle;
        this.author = newAuthor;
        this.price = newPrice;
        this.quantityOfStock = newQuantityOfStock; 

    }

    //Getters
    public Long getID() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantityOfStock() {
        return this.quantityOfStock;
    }

    //Setter for modifying price state
    public void setPrice(double modifiedPrice) {
        this.price = modifiedPrice;

    }

     //Setter for modifying stock state
    public void setQuantityOfStock(int modifiedQuantityOfStock) {
        this.quantityOfStock = modifiedQuantityOfStock; 
    }
    
    //Modifies the price in the new instance w/o mutating the existing price
    public Book applyDiscount(double discountPercent) {
        double discountedPrice = price - (price * discountPercent / 100);
        return new Book(title, author, discountedPrice, quantityOfStock);
    }

    public void setTitle(String modifiedTitle) {
        this.title = modifiedTitle;
    }

    public void setAuthor(String modifiedAuthor) {
        this.author = modifiedAuthor;
    }

}
