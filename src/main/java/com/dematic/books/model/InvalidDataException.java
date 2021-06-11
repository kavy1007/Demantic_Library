package com.dematic.books.model;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends RuntimeException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public InvalidDataException(String s) {
        super(s);
    }
}
