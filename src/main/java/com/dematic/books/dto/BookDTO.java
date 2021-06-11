package com.dematic.books.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class BookDTO {
    @NotNull(message = "name is Mandatory")
    private String name;
    @NotNull(message = "barcode is Mandatory")
    private String author;
    @NotNull(message = "barcode is Mandatory")
    private String barcode;
    @PositiveOrZero(message = "quantity should be greater than 0")
    private int quantity;
    @PositiveOrZero(message = "price should be greater than 0")
    private BigDecimal price;
    @Max(value = 1900, message = "releaseYear should be less than than 1900")
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
