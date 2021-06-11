package com.dematic.books.repository;

import com.dematic.books.dao.BookDAO;
import com.dematic.books.model.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRepository {
    @Autowired
    private IBookRepository bookRepository;

    public void save(List<BookDAO> bookDAOList) {
        bookRepository.saveAll(bookDAOList);
    }

    public List<BookDAO> getBooksByBarCode(String barCode) {

        return bookRepository
                .findByBarcode(barCode)
                .orElseThrow(() -> new InvalidDataException("The Book details Are not found for the barCode " + barCode));
    }

}
