package com.dematic.books.controller;

import com.dematic.books.dto.Library;
import com.dematic.books.model.InvalidDataException;
import com.dematic.books.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
public class BookAPI implements IBookAPI {
    @Autowired
    private IBookService bookService;

    @Autowired
    private Validator validator;

    @Override
    public ResponseEntity addBooks(Library libraries) {
        Set<ConstraintViolation<Library>> violations = validator.validate(libraries);
        if (!violations.isEmpty()) {
            throw new InvalidDataException(
                    "The  input sent is invalid."
                            + "The violations are  "
                            + violations
                            .stream()
                            .map(
                                    constraintViolation ->
                                            constraintViolation.getMessageTemplate()
                                                    + "-->"
                                                    + constraintViolation.getPropertyPath())
                            .collect(Collectors.toList()));
            
        }
        bookService.saveBooks(libraries);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateBookDetails(String barcode, Library libraries) {
        bookService.updateBooksByBarCode(barcode, libraries);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Library> getBooksByBarCode(String barcode) {
        return new ResponseEntity<>(bookService.getBooksByBarCode(barcode), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Library> calculateTotal(String barcode) {
        return new ResponseEntity<>(bookService.calculateTotal(barcode), HttpStatus.OK);
    }
}
