package com.dematic.books.service;

import com.dematic.books.dto.Library;

public interface IBookDecorator {
    Library calculateTotal(String barCode);

}
