package com.btl.oop.service;

import com.btl.oop.dto.BookDto;
import com.btl.oop.entity.Book;
import com.btl.oop.repository.BookRepository;
import com.btl.oop.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book1;
    private Book book2;
    private BookDto bookDto;

    @BeforeEach
    void setUp() {
        book1 = new Book(1L, "Lập Trình Java", "Dev C", 50.0, 10);
        book2 = new Book(2L, "Spring Boot Cơ Bản", "Dev A", 80.0, 5);
        bookDto = new BookDto();
        bookDto.setTitle("Sách Mới");
        bookDto.setAuthor("Dev B");
        bookDto.setPrice(120.0);
        bookDto.setStock(20);
    }

    // --- TEST CREATE/UPDATE/DELETE ---

    @Test
    void create_ShouldSaveBookAndReturnIt() {
        // Khi BookRepository.save() được gọi, trả về book1 (đã gán ID)
        when(bookRepository.save(any(Book.class))).thenReturn(book1);

        // Thực thi phương thức cần test
        Book createdBook = bookService.create(bookDto);

        // Khẳng định kết quả
        assertNotNull(createdBook);
        assertEquals("Lập Trình Java", createdBook.getTitle());

        // Khẳng định BookRepository.save đã được gọi 1 lần
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void update_ShouldUpdateExistingBook() {
        Long bookId = 1L;
        // Dữ liệu mới từ DTO
        BookDto updateDto = new BookDto();
        updateDto.setTitle("Java Nâng Cao");
        updateDto.setAuthor("Dev C");
        updateDto.setPrice(150.0);
        updateDto.setStock(15);

        // Mock hành vi:
        // 1. findById trả về book1 hiện có
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book1));
        // 2. save trả về book1 (sau khi đã update)
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Thực thi
        Book updatedBook = bookService.update(bookId, updateDto);

        // Khẳng định
        assertNotNull(updatedBook);
        assertEquals("Java Nâng Cao", updatedBook.getTitle());
        assertEquals(150.0, updatedBook.getPrice());
    }

    @Test
    void update_ShouldThrowExceptionIfBookNotFound() {
        Long bookId = 99L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Khẳng định rằng khi update sách không tồn tại sẽ ném ra RuntimeException
        assertThrows(RuntimeException.class, () -> bookService.update(bookId, bookDto));
    }

    @Test
    void delete_ShouldCallDeleteById() {
        Long bookId = 1L;
        // Thực thi
        bookService.delete(bookId);

        // Khẳng định rằng BookRepository.deleteById đã được gọi đúng 1 lần với ID đó
        verify(bookRepository, times(1)).deleteById(bookId);
    }

    // --- TEST SEARCH BY KEYWORD (list) ---

    @Test
    void list_ShouldReturnPageOfBooksWhenNoSearchQuery() {
        // Mock dữ liệu
        Pageable pageable = PageRequest.of(0, 10);
        List<Book> bookList = Arrays.asList(book1, book2);
        Page<Book> expectedPage = new PageImpl<>(bookList, pageable, bookList.size());

        // Mock hành vi: findAll(pageable) trả về trang sách
        when(bookRepository.findAll(pageable)).thenReturn(expectedPage);

        // Thực thi
        Page<Book> actualPage = bookService.list(pageable, null);

        // Khẳng định
        assertEquals(2, actualPage.getTotalElements());
        assertEquals(book1.getTitle(), actualPage.getContent().get(0).getTitle());
        verify(bookRepository, times(1)).findAll(pageable);
        verify(bookRepository, times(0)).findByTitleContainingIgnoreCase(anyString());
    }

    @Test
    void list_ShouldReturnBooksMatchingKeyword() {
        String keyword = "java";
        Pageable pageable = PageRequest.of(0, 10);
        List<Book> matchingBooks = Arrays.asList(book1);

        // Mock hành vi: findByTitleContainingIgnoreCase trả về sách khớp từ khóa
        when(bookRepository.findByTitleContainingIgnoreCase(keyword)).thenReturn(matchingBooks);
        // Mock count cho mục đích phân trang của PageImpl
        when(bookRepository.count()).thenReturn(2L);

        // Thực thi
        Page<Book> actualPage = bookService.list(pageable, keyword);

        // Khẳng định
        assertEquals(1, actualPage.getContent().size());
        assertEquals(book1.getTitle(), actualPage.getContent().get(0).getTitle());

        // Khẳng định rằng phương thức tìm kiếm đã được gọi
        verify(bookRepository, times(1)).findByTitleContainingIgnoreCase(keyword);
        verify(bookRepository, times(0)).findAll(any(Pageable.class));
    }
}