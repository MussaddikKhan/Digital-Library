package com.myProject.practice.Digital_Library_Project.Services;

import com.myProject.practice.Digital_Library_Project.Entity.Book;
import com.myProject.practice.Digital_Library_Project.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private  BookRepository bookRepository;
    public void saveBook(Book book){
           bookRepository.save(book);
    }
    public ResponseEntity<Optional<Book>> getBookById(Integer id){

        Optional<Book> book =  bookRepository.findById(id);
        if(book == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(book);
        }

    }
    public void updateBook(Book book){
//        bookRepository.updateBook(book);
    }
    public  ResponseEntity<Optional<Book>> deleteBookById(Integer id){
        Optional<Book> book =  bookRepository.findById(id);
        if(book == null){
            return ResponseEntity.notFound().build();
        }
        else{
            bookRepository.deleteById(id);
            return ResponseEntity.ok(book);
        }
    }
}

