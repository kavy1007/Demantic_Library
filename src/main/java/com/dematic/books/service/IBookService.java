package com.dematic.books.service;

import com.dematic.books.dto.Library;

public interface IBookService {
    Library calculateTotal(String barCode);

    void saveBooks(Library library);

    void updateBooksByBarCode(String barCode, Library library);

    Library getBooksByBarCode(String barCode);
}
