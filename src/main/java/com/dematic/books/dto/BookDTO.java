package com.dematic.books.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class BookDTO {
    @NonNull
    private String name;
    @NonNull
    private String author;
    @NonNull
    private String barcode;
    @PositiveOrZero
    private int quantity;
    @PositiveOrZero
    private BigDecimal price;
    @Max(1900)
    private long releaseYear;
    private int scienceIndex;
    private long bookId;

    public BookDTO(String name, String author, String barcode, int quantity, BigDecimal price) {
        this.barcode = barcode;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    public BookDTO(String name, String author, String barcode, int quantity, BigDecimal price,
                   int scienceIndex, long releaseYear, long bookId) {
        this(name, author, barcode, quantity, price);
        this.scienceIndex = scienceIndex;
        this.releaseYear = releaseYear;
        this.bookId = bookId;
    }
}
