package com.myProject.practice.Digital_Library_Project.Controller;


import com.myProject.practice.Digital_Library_Project.Entity.Book;
import com.myProject.practice.Digital_Library_Project.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/create")
    public  void createBook(@RequestBody Book book){
        bookService.saveBook(book);
    }
    @PostMapping("/update")
    public  void updateBook(@RequestBody Book book){
        bookService.updateBook(book);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<Optional<Book>> deleteBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

}
