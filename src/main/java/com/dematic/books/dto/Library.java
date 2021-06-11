package com.dematic.books.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Library {
    @Valid
    private List<BookDTO> books;
    private BigDecimal total;

    public Library(List<BookDTO> books) {
        this.books = books;
    }

    public Library(BigDecimal total) {
        this.total = total;
    }
}
