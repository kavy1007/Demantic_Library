package com.dematic.books.dao;

import com.dematic.books.dto.BookDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "BOOK")
@Getter

@NoArgsConstructor
public class BookDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public BookDAO(String name, String author, String barcode, int quantity, BigDecimal price,
                   String bookType, long releaseYear, int scienceIndex) {
        this(name, author, barcode, quantity, price, bookType);
        this.releaseYear = releaseYear;
        this.scienceIndex = scienceIndex;

    }

    public void updateBook(BookDTO bookDTO) {
        setAuthor(bookDTO.getAuthor());
        setBarcode(bookDTO.getBarcode());
        setName(bookDTO.getName());
        setPrice(bookDTO.getPrice());
        setReleaseYear(bookDTO.getReleaseYear());
        setQuantity(bookDTO.getQuantity());
        setScienceIndex(bookDTO.getScienceIndex());
    }

    public void setName(String name) {
        if (Objects.nonNull(name))
            this.name = name;
    }

    public void setAuthor(String author) {
        if (Objects.nonNull(author))
            this.author = author;
    }

    public void setBarcode(String barcode) {
        if (Objects.nonNull(barcode))
            this.barcode = barcode;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0)
            this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        if (Objects.nonNull(price))
            this.price = price;
    }

    public void setReleaseYear(long releaseYear) {
        if (releaseYear > 0)
            this.releaseYear = releaseYear;
    }

    public void setScienceIndex(int scienceIndex) {
        if (scienceIndex > 0)
            this.scienceIndex = scienceIndex;
    }


}
