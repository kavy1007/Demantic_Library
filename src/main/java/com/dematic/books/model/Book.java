package com.dematic.books.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Book {
    private String name;
    private String author;
    private String barcode;
    private int quantity;
    private BigDecimal price;

    public Book(String name, String author, String barcode, int quantity, BigDecimal price) {
        this.name = name;
        this.author = author;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal calculateTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
