package com.btl.oop.controller;

import com.btl.oop.dto.BookDto;
import com.btl.oop.entity.Book;
import com.btl.oop.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String q,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookService.list(pageable, q);
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "books/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.create(bookDto);
        return "redirect:/admin/books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.get(id);
        if (book.isPresent()) {
            // Chuyển đổi từ Entity sang DTO để hiển thị trên form
            BookDto bookDto = new BookDto();
            bookDto.setId(book.get().getId());
            bookDto.setTitle(book.get().getTitle());
            bookDto.setAuthor(book.get().getAuthor());
            bookDto.setPrice(book.get().getPrice());
            bookDto.setStock(book.get().getStock());
            model.addAttribute("book", bookDto);
            return "books/form";
        }
        return "redirect:/admin/books";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("book") BookDto bookDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.update(id, bookDto);
        return "redirect:/admin/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/admin/books";
    }
}
