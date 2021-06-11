package com.dematic.books.controller;

import com.dematic.books.dto.Library;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "book")
public interface IBookAPI {
    @PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
    ResponseEntity<Library> addBooks(@RequestBody Library libraries);

    @PostMapping(value = "/{barcode}", produces = "application/json", consumes = "application/json")
    ResponseEntity<Library> updateBookDetails(@PathVariable("barcode")
                                                      String barcode, @RequestBody Library libraries);

    @GetMapping(value = "/{barcode}", produces = "application/json")
    ResponseEntity<Library> getBooksByBarCode(@PathVariable("barcode")
                                                      String barcode);

    @GetMapping(value = "/calculatetotal/{barcode}", produces = "application/json")
    ResponseEntity<Library> calculateTotal(@PathVariable("barcode")
                                                   String barcode);


}
