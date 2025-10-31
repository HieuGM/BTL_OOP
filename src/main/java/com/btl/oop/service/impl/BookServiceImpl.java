package com.btl.oop.service.impl;

import com.btl.oop.dto.BookDto;
import com.btl.oop.entity.Book;
import com.btl.oop.repository.BookRepository;
import com.btl.oop.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Page<Book> list(Pageable pageable, String q) {
        if (q != null && !q.isBlank()) {
            return bookRepository.findByTitleContainingIgnoreCase(q.trim(), pageable);
        }
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> get(UUID id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book create(BookDto dto) {
        Book b = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
        return bookRepository.save(b);
    }

    @Override
    public Book update(UUID id, BookDto dto) {
        Book b = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + id));
        b.setTitle(dto.getTitle());
        b.setAuthor(dto.getAuthor());
        b.setPrice(dto.getPrice());
        b.setStock(dto.getStock());
        return bookRepository.save(b);
    }

    @Override
    public Book patch(UUID id, Map<String, Object> fields) {
        Book b = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + id));

        for (var entry : fields.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();

            if (!("title".equals(key) || "author".equals(key) || "price".equals(key) || "stock".equals(key))) {
                continue;
            }
            Field f = ReflectionUtils.findField(Book.class, key);
            if (f != null) {
                f.setAccessible(true);
                if ("price".equals(key) && val instanceof Number num) {
                    ReflectionUtils.setField(f, b, num.doubleValue());
                } else if ("stock".equals(key) && val instanceof Number num) {
                    ReflectionUtils.setField(f, b, num.intValue());
                } else if (val != null) {
                    ReflectionUtils.setField(f, b, val);
                }
            }
        }
        return bookRepository.save(b);
    }

    @Override
    public void delete(UUID id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}