package com.myProject.practice.Digital_Library_Project.Controller;


import com.myProject.practice.Digital_Library_Project.Entity.Author;
import com.myProject.practice.Digital_Library_Project.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController
{
    @Autowired
    private AuthorService authorService;
    @PostMapping("/create")
    public  void createAuthor(@RequestBody Author author){
        authorService.saveAuthor(author);
    }
    @PostMapping("/update")
    public  void updateAuthor(@RequestBody  Author author){
        authorService.updateAuthor(author);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Author>> getAuthorById(@PathVariable Integer id) {
        return authorService.getAuthorById(id);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<Optional<Author>> deleteAuthorById(@PathVariable Integer id) {
        return authorService.deleteAuthorById(id);
    }
}
