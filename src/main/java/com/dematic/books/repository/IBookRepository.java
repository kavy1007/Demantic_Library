package com.dematic.books.repository;

import com.dematic.books.dao.BookDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<BookDAO, Long> {
    Optional<List<BookDAO>> findByBarcode(String barCode);
}
