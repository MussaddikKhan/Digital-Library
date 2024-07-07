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

    public Author createOrGet(Author author){
        Author authorFromDb = authorRepository.findByEmail(author.getEmail());
        if(authorFromDb == null){
            authorFromDb = authorRepository.save(author);
        }
       return  authorFromDb;
    }
    public  Author getAuthor(String email){
        return authorRepository.findByEmail(email);
    }
}
