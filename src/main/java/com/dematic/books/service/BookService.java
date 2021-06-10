package com.dematic.books.service;

import com.dematic.books.dao.BookDAO;
import com.dematic.books.dto.BookDTO;
import com.dematic.books.dto.Library;
import com.dematic.books.model.AntiqueJournal;
import com.dematic.books.model.Book;
import com.dematic.books.model.ScienceJournal;
import com.dematic.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    private static String GENERAL = "GENERAL";
    private static String ANTIQUE = "ANTIQUE";
    private static String SCIENCE = "SCIENCE";

    public void saveBooks(Library library) {
        List<BookDAO> books = library.getBooks().stream().map(book -> {
            if (Objects.nonNull(book.getReleaseYear())) {
                return getAntiqueJournal(book);
            }
            if (Objects.nonNull(book.getScienceIndex())) {
                return getScienceJournal(book);
            }
            return getBooks(book);
        }).collect(Collectors.toList());
        bookRepository.save(books);
    }

    public void updateBooksByBarCode(String barCode) {

    }

    public Library getBooksByBarCode(String barCode) {
        return new Library(bookRepository.getBooksByBarCode(barCode).stream()
                .map(this::getBookDTO).collect(Collectors.toList()));
    }

    public Library calculateTotal(String barCode) {
        BigDecimal total = bookRepository.getBooksByBarCode(barCode).stream().map(bookDAO -> {
            if ("Science".equalsIgnoreCase(bookDAO.getBookType())) {
                ScienceJournal scienceJournal = getScienceJournal(bookDAO);
                return scienceJournal.calculateTotal();
            }
            if ("Antique".equalsIgnoreCase(bookDAO.getBookType())) {
                AntiqueJournal antiqueJournal = getAntiqueJournal(bookDAO);
                return antiqueJournal.calculateTotal();
            }
            Book book = getBooks(bookDAO);
            return book.calculateTotal();
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Library(total);
    }

    private AntiqueJournal getAntiqueJournal(BookDAO bookDAO) {
        return new AntiqueJournal(bookDAO.getName(),
                bookDAO.getAuthor(),
                bookDAO.getBarcode(),
                bookDAO.getQuantity(),
                bookDAO.getPrice(),
                bookDAO.getReleaseYear());
    }

    private Book getBooks(BookDAO bookDAO) {
        return new Book(bookDAO.getName(),
                bookDAO.getAuthor(),
                bookDAO.getBarcode(),
                bookDAO.getQuantity(),
                bookDAO.getPrice());
    }

    private ScienceJournal getScienceJournal(BookDAO bookDAO) {
        return new ScienceJournal(bookDAO.getName(),
                bookDAO.getAuthor(),
                bookDAO.getBarcode(),
                bookDAO.getQuantity(),
                bookDAO.getPrice(),
                bookDAO.getScienceIndex());
    }

    private BookDAO getAntiqueJournal(BookDTO bookDTO) {
        return new BookDAO(bookDTO.getName(),
                bookDTO.getAuthor(),
                bookDTO.getBarcode(),
                bookDTO.getQuantity(),
                bookDTO.getPrice(),
                ANTIQUE,
                bookDTO.getReleaseYear());
    }

    private BookDAO getBooks(BookDTO bookDTO) {
        return new BookDAO(bookDTO.getName(),
                bookDTO.getAuthor(),
                bookDTO.getBarcode(),
                bookDTO.getQuantity(),
                bookDTO.getPrice(), GENERAL);
    }

    private BookDAO getScienceJournal(BookDTO bookDTO) {
        return new BookDAO(bookDTO.getName(),
                bookDTO.getAuthor(),
                bookDTO.getBarcode(),
                bookDTO.getQuantity(),
                bookDTO.getPrice(),
                SCIENCE,
                bookDTO.getScienceIndex());
    }


    private BookDTO getBookDTO(BookDAO bookDTO) {
        return new BookDTO(bookDTO.getName(),
                bookDTO.getAuthor(),
                bookDTO.getBarcode(),
                bookDTO.getQuantity(),
                bookDTO.getPrice(),
                bookDTO.getScienceIndex());
    }
}

