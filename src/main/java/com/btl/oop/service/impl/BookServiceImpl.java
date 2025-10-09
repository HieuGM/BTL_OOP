package com.btl.oop.service.impl;

import com.btl.oop.entity.Book;
import com.btl.oop.repository.BookRepository;
import com.btl.oop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> list(Pageable pageable, String q) {
        if (q != null && !q.isEmpty()) {
            return new PageImpl<>(bookRepository.findByTitleContainingIgnoreCase(q), pageable, bookRepository.count());
        }
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> get(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book book) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            existingBook.setStock(book.getStock());
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}