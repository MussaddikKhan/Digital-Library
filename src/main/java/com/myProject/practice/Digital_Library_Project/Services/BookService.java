package com.myProject.practice.Digital_Library_Project.Services;

import com.myProject.practice.Digital_Library_Project.Dto.CreatedBookRequest;
import com.myProject.practice.Digital_Library_Project.Dto.ReturnBookResponse;
import com.myProject.practice.Digital_Library_Project.Entity.Author;
import com.myProject.practice.Digital_Library_Project.Entity.Book;
import com.myProject.practice.Digital_Library_Project.Entity.Student;
import com.myProject.practice.Digital_Library_Project.Exceptions.AuthorNotFoundException;
import com.myProject.practice.Digital_Library_Project.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;

    @Autowired
    BookCacheService bookCacheService;

    public ReturnBookResponse saveBook(CreatedBookRequest createdBookRequest) {
        Book book = createdBookRequest.to();
        Author author = authorService.createOrGet(book.getAuthor());
        book.setAuthor(author);
        bookRepository.save(book);
        ReturnBookResponse returnBookResponse = ReturnBookResponse.from(book);
        return returnBookResponse;
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);

    }
    public Book getBookByName(String bookName) {
        Book book = bookCacheService.getBookFromCache(bookName);
        if (book == null) {
            book = bookRepository.getBookByName(bookName);
            if (book == null) return null;
            bookCacheService.setBookInCache(book);
        }
        return book;
    }

    public void updateBookById(Integer id, Student student) {
        bookRepository.updateBook(id, student);
    }

    public List<Book> getFilteredBooks(String name, String genre, String language, String authorEmail) {
        Author author = null;
        if (authorEmail != null) {
            author = authorService.getAuthor(authorEmail);
            if (author == null) {
                try {
                    throw new AuthorNotFoundException("This Author not Present in DB");
                } catch (AuthorNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return bookRepository.findBooksByFilters(name, genre, language, author);
    }
}

