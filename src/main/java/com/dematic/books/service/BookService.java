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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    private static String GENERAL = "GENERAL";
    private static String ANTIQUE = "ANTIQUE";
    private static String SCIENCE = "SCIENCE";

    public void saveBooks(Library library) {
        List<BookDAO> books = library.getBooks().stream().map(this::getBooks).collect(Collectors.toList());
        bookRepository.save(books);
    }

    public void updateBooksByBarCode(String barCode, Library library) {
        Map<Long, BookDAO> books = bookRepository.getBooksByBarCode(barCode).stream()
                .collect(Collectors.toMap(BookDAO::getBookId, Function.identity()));
        List<BookDAO> updatedBooks = library.getBooks().stream()
                .map(bookDTO -> {
                    BookDAO bookEntity = books.get(bookDTO.getBookId());
                    bookEntity.updateBook(bookDTO);
                    return bookEntity;
                }).collect(Collectors.toList());
        bookRepository.save(updatedBooks);
    }


    public Library getBooksByBarCode(String barCode) {
        return new Library(bookRepository.getBooksByBarCode(barCode).stream()
                .map(this::getBookDTO).collect(Collectors.toList()));
    }

    public Library calculateTotal(String barCode) {
        BigDecimal total = bookRepository.getBooksByBarCode(barCode).stream().map(bookDAO -> {
            if (SCIENCE.equalsIgnoreCase(bookDAO.getBookType())) {
                ScienceJournal scienceJournal = getScienceJournal(bookDAO);
                return scienceJournal.calculateTotal();
            }
            if (ANTIQUE.equalsIgnoreCase(bookDAO.getBookType())) {
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

    private BookDAO getBooks(BookDTO bookDTO) {
        String bookType = GENERAL;
        if (bookDTO.getReleaseYear() > 0) {
            bookType = ANTIQUE;
        }
        if (bookDTO.getScienceIndex() > 0) {
            bookType = SCIENCE;
        }
        return new BookDAO(bookDTO.getName(),
                bookDTO.getAuthor(),
                bookDTO.getBarcode(),
                bookDTO.getQuantity(),
                bookDTO.getPrice(),
                bookType,
                bookDTO.getReleaseYear(),
                bookDTO.getScienceIndex());
    }

    private BookDTO getBookDTO(BookDAO bookDTO) {
        return new BookDTO(bookDTO.getName(),
                bookDTO.getAuthor(),
                bookDTO.getBarcode(),
                bookDTO.getQuantity(),
                bookDTO.getPrice(),
                bookDTO.getScienceIndex(), bookDTO.getReleaseYear(), bookDTO.getBookId());
    }
}

