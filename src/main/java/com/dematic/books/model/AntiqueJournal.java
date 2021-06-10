package com.dematic.books.model;

import java.math.BigDecimal;
import java.time.Year;

public class AntiqueJournal extends Book {
    private long releaseYear;

    public AntiqueJournal(String name, String author, String barcode, int quantity, BigDecimal price, long releaseYear) {
        super(name, author, barcode, quantity, price);
        this.releaseYear = releaseYear;
    }

    public BigDecimal calculateTotal() {
        return super.calculateTotal().multiply(new BigDecimal(Year.now().minusYears(releaseYear).toString()));
    }
}
