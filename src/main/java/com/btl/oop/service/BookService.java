package com.btl.oop.service;

import com.btl.oop.dto.BookDto;
import com.btl.oop.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface BookService {

    Page<Book> list(Pageable pageable, String q);
    Optional<Book> get(Long id);

    Book create(BookDto bookDto);
    Book update(Long id, BookDto bookDto);

    void delete(Long id);
}