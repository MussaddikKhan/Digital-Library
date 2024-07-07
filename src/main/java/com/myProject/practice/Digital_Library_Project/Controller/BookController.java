package com.myProject.practice.Digital_Library_Project.Controller;


import com.myProject.practice.Digital_Library_Project.Dto.CreatedBookRequest;
import com.myProject.practice.Digital_Library_Project.Dto.ReturnBookResponse;
import com.myProject.practice.Digital_Library_Project.Entity.Book;
import com.myProject.practice.Digital_Library_Project.Entity.Genre;
import com.myProject.practice.Digital_Library_Project.Services.BookService;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/create")
    public ReturnBookResponse createBook(@Valid @RequestBody CreatedBookRequest bookRequest){
        return bookService.saveBook(bookRequest);
    }
    @GetMapping("/{bookName}")
    public Book getBookByName(@PathVariable String bookName) {
        return bookService.getBookByName(bookName);
    }
    @GetMapping("/filter")
    public List<Book> get(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "genre", required = false) String genre,
                          @RequestParam(value = "language", required = false) String language,
                          @RequestParam(value = "authorEmail", required = false) String authorEmail) {
        return bookService.getFilteredBooks(name, genre, language, authorEmail);
    }
}
