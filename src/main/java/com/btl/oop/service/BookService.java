package com.btl.oop.service;

import com.btl.oop.dto.BookDto;
import com.btl.oop.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    Page<Book> list(Pageable pageable, String q);

    Optional<Book> get(UUID id);

    Book create(BookDto bookDto);

    Book update(UUID id, BookDto bookDto);

    Book patch(UUID id, Map<String, Object> fields);
    void delete(UUID id);
    List<Book> getBooks();
}