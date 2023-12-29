package com.practice.book.model;

public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private double price;
    private int quantityOfStock;

    public BookDTO(Long id, String title, String author, double price, int quantityOfStock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantityOfStock = quantityOfStock;

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

    //Setters - none for ID since generated in @entity
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setAuthor(String newAuthor) {
        this.title = newAuthor;
    }

    public void setQuantityOfStock(int newQuantityOfStock) {
        this.quantityOfStock = newQuantityOfStock;
    }

    //Conversion methods TODO
    public BookDTO fromEntity(Book book) {
        return new BookDTO(id, title, author, price, quantityOfStock);
    }

    public Book toEntity(BookDTO bookDTO) {
        return new Book();
    }
}
