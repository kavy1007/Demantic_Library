package com.dematic.books.dao;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "BOOK")
@Getter
public class BookDAO {
    private long bookId;
    private String name;
    private String author;
    private String barcode;
    private int quantity;
    private BigDecimal price;
    private long releaseYear;
    private int scienceIndex;
    private String bookType;

    public BookDAO(String name, String author, String barcode, int quantity, BigDecimal price, String bookType) {
        this.barcode = barcode;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
        this.bookType = bookType;
    }

    public BookDAO(String name, String author, String barcode, int quantity, BigDecimal price, String bookType, int scienceIndex) {
        this(name, author, barcode, quantity, price, bookType);
        this.scienceIndex = scienceIndex;
    }

    public BookDAO(String name, String author, String barcode, int quantity, BigDecimal price, String bookType, long releaseYear) {
        this(name, author, barcode, quantity, price, bookType);
        this.releaseYear = releaseYear;
    }
}