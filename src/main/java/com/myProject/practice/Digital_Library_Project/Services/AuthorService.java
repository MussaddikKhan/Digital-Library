package com.myProject.practice.Digital_Library_Project.Services;

import com.myProject.practice.Digital_Library_Project.Entity.Author;
import com.myProject.practice.Digital_Library_Project.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void saveAuthor(Author author){
        authorRepository.save(author);
    }
    public ResponseEntity<Optional<Author>> getAuthorById(Integer id){

        Optional<Author> author =  authorRepository.findById(id);
        if(author == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(author);
        }

    }
    public void updateAuthor(Author author){
//        authorRepository.updateAuthor(author);
    }
    public ResponseEntity<Optional<Author>> deleteAuthorById(Integer id){
        Optional<Author> author =  authorRepository.findById(id);
        if(author == null){
            return ResponseEntity.notFound().build();
        }
        else{
            authorRepository.deleteById(id);
            return ResponseEntity.ok(author);
        }
    }
}
