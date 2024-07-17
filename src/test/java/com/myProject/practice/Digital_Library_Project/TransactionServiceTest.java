package com.myProject.practice.Digital_Library_Project;

import com.myProject.practice.Digital_Library_Project.Dto.CreateTransactionRequest;
import com.myProject.practice.Digital_Library_Project.Entity.*;
import com.myProject.practice.Digital_Library_Project.Exceptions.BookAlreadyIssued;
import com.myProject.practice.Digital_Library_Project.Exceptions.BookLimitExceed;
import com.myProject.practice.Digital_Library_Project.Repository.TransactionRepository;
import com.myProject.practice.Digital_Library_Project.Services.BookService;
import com.myProject.practice.Digital_Library_Project.Services.StudentService;
import com.myProject.practice.Digital_Library_Project.Services.TransactionService;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
    @InjectMocks
    @Spy
    TransactionService transactionService;
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    StudentService studentService;
    @Mock
    BookService bookService;

    @Test
    public void getTxnById(){
        String externalTxnId = UUID.randomUUID().toString();
        Transaction t = Transaction.builder()
                .externalTxnId(externalTxnId)
                .build();
        Mockito.when(transactionRepository.findByExternalTxnId(externalTxnId)).thenReturn(t);
        Transaction newT = transactionService.getTxnById(externalTxnId);
        assertEquals(t, newT); 
    }

    // Transact Issue Transaction Case
    @Test
    public void issueTransactionTest() throws Exception {

        int studentId  = 1;
        int bookId = 1;
        String externalTxnId = UUID.randomUUID().toString();
        Student student =  Student.builder()
                .id(studentId)
                .age(21)
                .email("musaddik123@gmail.com")
                .name("Musaddik Khan")
                .phone_Number("8908909008")
                .rollNo("BCA-TY-55")
                .build();

        Book book = Book.builder()
                .id(bookId)
                .genre(Genre.FICTIONAL)
                .language("English")
                .name("Romeo & Juliet ")
                .author(Author.builder()
                        .country("USA")
                        .id(1)
                        .email("William@gmail.com")
                        .name("William Shakespeare")
                        .build())
                .build();


        Transaction txn = Transaction.builder()
                .book(book)
                .transactionType(TransactionType.ISSUE)
                .transactionStatus(TransactionStatus.SUCESSFULL)
                .externalTxnId(externalTxnId)
                .student(student)
                .build();
        
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(txn);
        Mockito.doNothing().when(bookService).updateBookById(bookId, student);
        String returnedTxnId = transactionService.issueBook(student, book, TransactionType.ISSUE);
        assertEquals(externalTxnId, returnedTxnId);

    }

    // Transact Return Transaction Case

    @Test
    public  void  returnTransactionTest() throws Exception {
        int studentId  = 1;
        int bookId = 1;
        String externalTxnId = UUID.randomUUID().toString();
        Student student =  Student.builder()
                .id(studentId)
                .age(21)
                .email("musaddik123@gmail.com")
                .name("Musaddik Khan")
                .phone_Number("8908909008")
                .rollNo("BCA-TY-55")
                .build();

        Book book = Book.builder()
                .id(bookId)
                .genre(Genre.FICTIONAL)
                .language("English")
                .name("Romeo & Juliet ")
                .author(Author.builder()
                        .country("USA")
                        .id(1)
                        .email("William@gmail.com")
                        .name("William Shakespeare")
                        .build())
                .build();

        Transaction t = Transaction.builder()
                .book(book)
                .transactionType(TransactionType.ISSUE)
                .transactionStatus(TransactionStatus.SUCESSFULL)
                .externalTxnId(externalTxnId)
                .student(student)
                .build();

        Transaction txn = Transaction.builder()
                .book(book)
                .transactionType(TransactionType.ISSUE)
                .transactionStatus(TransactionStatus.SUCESSFULL)
                .externalTxnId(externalTxnId)
                .student(student)
                .build();
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(t);

        Mockito.when(transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(book , student, TransactionType.ISSUE, TransactionStatus.SUCESSFULL)).thenReturn(txn);
          Mockito.lenient().doNothing().when(bookService).updateBookById(bookId, null);

        String returnedTxnId = transactionService.returnBook(student, book, TransactionType.RETURN);

        Mockito.verify(transactionService, Mockito.times(1)).getFine(Mockito.any(), Mockito.any());

        assertEquals(externalTxnId, returnedTxnId);
    }

    // Book Already Issued  Exception Failure TestCase
    @Test(expected = BookAlreadyIssued.class)
    public  void bookAlreadyIssuedTest() throws Exception {
        int studentId  = 1;
        int bookId = 1;
        String externalTxnId = UUID.randomUUID().toString();
        Student student =  Student.builder()
                .id(studentId)
                .age(21)
                .email("musaddik123@gmail.com")
                .name("Musaddik Khan")
                .phone_Number("8908909008")
                .rollNo("BCA-TY-55")
                .build();

        Book book = Book.builder()
                .id(bookId)
                .genre(Genre.FICTIONAL)
                .language("English")
                .name("Romeo & Juliet ")
                .author(Author.builder()
                        .country("USA")
                        .id(1)
                        .email("William@gmail.com")
                        .name("William Shakespeare")
                        .build())
                .student(student)
                .build();

        Transaction txn = Transaction.builder()
                .book(book)
                .transactionType(TransactionType.ISSUE)
                .transactionStatus(TransactionStatus.SUCESSFULL)
                .externalTxnId(externalTxnId)
                .student(student)
                .build();
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(txn);

        transactionService.issueBook(student, book, TransactionType.ISSUE);
    }

    @Test(expected = BookLimitExceed.class)
    public  void bookLimitExceedTest() throws Exception {

        int studentId  = 1;
        int bookId = 1;
        String externalTxnId = UUID.randomUUID().toString();

        Book book = Book.builder()
                .id(bookId)
                .genre(Genre.FICTIONAL)
                .language("English")
                .name("Romeo & Juliet ")
                .author(Author.builder()
                        .country("USA")
                        .id(1)
                        .email("William@gmail.com")
                        .name("William Shakespeare")
                        .build())
                .build();


        List<Book> books = new ArrayList<>();
               books.add(book);
               books.add(Book.builder().id(2).name("Book Two").build());
               books.add(Book.builder().id(3).name("Book Three").build());

        Student student =  Student.builder()
                .id(studentId)
                .age(21)
                .email("musaddik123@gmail.com")
                .name("Musaddik Khan")
                .phone_Number("8908909008")
                .rollNo("BCA-TY-55")
                .bookList(books).build();

        Transaction txn = Transaction.builder()
                .book(book)
                .transactionType(TransactionType.ISSUE)
                .transactionStatus(TransactionStatus.SUCESSFULL)
                .externalTxnId(externalTxnId)
                .student(student)
                .build();
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(txn);

        transactionService.issueBook(student, book, TransactionType.ISSUE);
    }





}

