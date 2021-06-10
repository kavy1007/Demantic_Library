package com.dematic.books.model;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class ScienceJournal extends Book {
    private int scienceIndex;

    public ScienceJournal(String name, String author, String barcode, int quantity, BigDecimal price, int scienceIndex) {
        super(name, author, barcode, quantity, price);
        this.scienceIndex = scienceIndex;
    }

    public BigDecimal calculateTotal() {
        return super.calculateTotal().multiply(new BigDecimal(scienceIndex));
    }
}
