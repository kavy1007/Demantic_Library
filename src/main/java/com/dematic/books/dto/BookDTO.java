package com.dematic.books.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDTO {
    private String name;
    private String author;
    private String barcode;
    private int quantity;
    private BigDecimal price;
    private long releaseYear;
    private int scienceIndex;

    public BookDTO(String name, String author, String barcode, int quantity, BigDecimal price) {
        this.barcode = barcode;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    public BookDTO(String name, String author, String barcode, int quantity, BigDecimal price, int scienceIndex) {
        this(name, author, barcode, quantity, price);
        this.scienceIndex = scienceIndex;
    }

    public BookDTO(String name, String author, String barcode, int quantity, BigDecimal price, long releaseYear) {
        this(name, author, barcode, quantity, price);
        this.releaseYear = releaseYear;
    }
}
