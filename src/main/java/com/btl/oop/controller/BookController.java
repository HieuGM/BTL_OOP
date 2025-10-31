package com.btl.oop.controller;

import com.btl.oop.dto.BookDto;
import com.btl.oop.entity.Book;
import com.btl.oop.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String q,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookService.list(pageable, q);
        model.addAttribute("books", books);
        model.addAttribute("q", q);
        return "books/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "books/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("book") BookDto bookDto,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.create(bookDto);
        return "redirect:/admin/books";
    }

    @ResponseBody
    @GetMapping("/test")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        Optional<Book> bookOpt = bookService.get(id);
        if (bookOpt.isEmpty()) {
            return "redirect:/admin/books";
        }
        Book b = bookOpt.get();
        BookDto dto = new BookDto();
        dto.setId(b.getId());          // UUID
        dto.setTitle(b.getTitle());
        dto.setAuthor(b.getAuthor());
        dto.setPrice(b.getPrice());
        dto.setStock(b.getStock());
        model.addAttribute("book", dto);
        return "books/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable UUID id,
                         @Valid @ModelAttribute("book") BookDto bookDto,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.update(id, bookDto);
        return "redirect:/admin/books";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}