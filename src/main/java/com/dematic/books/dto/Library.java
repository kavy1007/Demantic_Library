package com.dematic.books.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class Library {
    private List<BookDTO> books;
    private BigDecimal total;

    public Library(List<BookDTO> books) {
        this.books = books;
    }

    public Library(BigDecimal total) {
        this.total = total;
    }
}
