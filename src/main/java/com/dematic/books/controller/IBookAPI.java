package com.dematic.books.controller;

import com.dematic.books.dto.Library;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "book")
@Api(value = "Library Module")
public interface IBookAPI {

    @ApiOperation(value = "Adds the list of Book into the Library")
    @PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
    ResponseEntity<Library> addBooks(@RequestBody Library libraries);

    @ApiOperation(value = "Updates the book details For the given barCode")
    @PostMapping(value = "/{barcode}", produces = "application/json", consumes = "application/json")
    ResponseEntity<Library> updateBookDetails(@PathVariable("barcode")
                                                      String barcode, @RequestBody Library libraries);

    @ApiOperation(value = "Get Books by BarCode")
    @GetMapping(value = "/{barcode}", produces = "application/json")
    ResponseEntity<Library> getBooksByBarCode(@PathVariable("barcode")
                                                      String barcode);

    @ApiOperation(value = "Calculate total price for the books in the given barcode")
    @GetMapping(value = "/calculatetotal/{barcode}", produces = "application/json")
    ResponseEntity<Library> calculateTotal(@PathVariable("barcode")
                                                   String barcode);


}
