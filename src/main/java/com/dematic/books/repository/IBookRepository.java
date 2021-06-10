package com.dematic.books.repository;

import com.dematic.books.dao.BookDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<BookDAO, Long> {
    List<BookDAO> findByBarcode(String barCode);
}
