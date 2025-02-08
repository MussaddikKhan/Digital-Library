//package com.myProject.practice.Digital_Library_Project;
//
//import com.myProject.practice.Digital_Library_Project.Dto.ReturnBookResponse;
//import com.myProject.practice.Digital_Library_Project.Dto.ReturnTransactionResponse;
//import com.myProject.practice.Digital_Library_Project.Entity.*;
//import com.myProject.practice.Digital_Library_Project.Exceptions.BookAlreadyIssued;
//import com.myProject.practice.Digital_Library_Project.Exceptions.BookIssuedAnotherStudentException;
//import com.myProject.practice.Digital_Library_Project.Exceptions.BookLimitExceed;
//import com.myProject.practice.Digital_Library_Project.Repository.TransactionRepository;
//import com.myProject.practice.Digital_Library_Project.Services.BookService;
//import com.myProject.practice.Digital_Library_Project.Services.StudentService;
//import com.myProject.practice.Digital_Library_Project.Services.TransactionService;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class TransactionServiceTest {
//
//    @InjectMocks
//    @Spy
//    private TransactionService transactionService;
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @Mock
//    private StudentService studentService;
//
//    @Mock
//    private BookService bookService;
//
//    @Test
//    public void getTxnById() {
//        String externalTxnId = UUID.randomUUID().toString();
//        Transaction t = Transaction.builder()
//                .externalTxnId(externalTxnId)
//                .build();
//
//        when(transactionRepository.findByExternalTxnId(externalTxnId)).thenReturn(t);
//
//        Transaction newT = transactionService.getTxnById(externalTxnId);
//
//        assertEquals(t, newT);
//    }
//
//    @Test
//    public void issueTransactionTest() throws Exception {
//        int studentId = 1;
//        int bookId = 1;
//        String externalTxnId = UUID.randomUUID().toString();
//
//        Student student = Student.builder().id(studentId).name("Musaddik Khan").build();
//        Book book = Book.builder().id(bookId).name("Romeo & Juliet").build();
//        Transaction txn = Transaction.builder().externalTxnId(externalTxnId).student(student).book(book).build();
//
//        when(transactionRepository.save(any())).thenReturn(txn);
//        doNothing().when(bookService).updateBookById(bookId, student);
//
//        String returnedTxnId = transactionService.issueBook(student, book, TransactionType.ISSUE);
//
//        assertEquals(externalTxnId, returnedTxnId);
//    }
//
//    @Test
//    public void returnTransactionTest() throws Exception {
//        int studentId = 1;
//        int bookId = 1;
//        String externalTxnId = UUID.randomUUID().toString();
//        Student student = Student.builder().id(studentId).name("Musaddik Khan").build();
//        Book book = Book.builder().id(bookId).name("Romeo & Juliet").student(student).build();
//
//        Transaction txn = Transaction.builder().externalTxnId(externalTxnId).student(student).book(book).build();
//
//        when(transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(any(), any(), any(), any()))
//                .thenReturn(txn);
//        when(transactionRepository.save(any())).thenReturn(txn);
//        doNothing().when(bookService).updateBookById(anyInt(), any());
//
//        String returnedTxnId = transactionService.returnBook(student, book, TransactionType.RETURN);
//
//        verify(transactionService, times(1)).getFine(any(), any());
//        assertEquals(externalTxnId, returnedTxnId);
//    }
//
//    @Test
//    public void bookAlreadyIssuedTest() {
//        Assertions.assertThrows(BookAlreadyIssued.class, () -> {
//            transactionService.issueBook(new Student(), new Book(), TransactionType.ISSUE);
//        });
//    }
//
//    @Test
//    public void bookLimitExceedTest() {
//        Assertions.assertThrows(BookLimitExceed.class, () -> {
//            transactionService.issueBook(new Student(), new Book(), TransactionType.ISSUE);
//        });
//    }
//
//    @Test
//    public void bookIsIssuedAnotherStudent() {
//        Assertions.assertThrows(BookIssuedAnotherStudentException.class, () -> {
//            transactionService.returnBook(new Student(), new Book(), TransactionType.RETURN);
//        });
//    }
//
//    @Test
//    public void returnTxnByTypeTest() {
//        Transaction transaction1 = Transaction.builder().transactionType(TransactionType.ISSUE).build();
//        Transaction transaction2 = Transaction.builder().transactionType(TransactionType.ISSUE).build();
//
//        when(transactionRepository.findByTransactionType(TransactionType.ISSUE))
//                .thenReturn(Arrays.asList(transaction1, transaction2));
//
//        List<ReturnTransactionResponse> actualResponse = transactionService.returnTransactionByType(TransactionType.ISSUE);
//
//        List<ReturnTransactionResponse> expectedResponse = Arrays.asList(
//                ReturnTransactionResponse.From(transaction1),
//                ReturnTransactionResponse.From(transaction2)
//        );
//
//        Assertions.assertIterableEquals(expectedResponse, actualResponse);
//    }
//
//    @Test
//    public void returnByTxnStatusTest() {
//        Transaction transaction1 = Transaction.builder().transactionStatus(TransactionStatus.SUCESSFULL).build();
//        Transaction transaction2 = Transaction.builder().transactionStatus(TransactionStatus.SUCESSFULL).build();
//
//        when(transactionRepository.findByTransactionStatus(TransactionStatus.SUCESSFULL))
//                .thenReturn(Arrays.asList(transaction1, transaction2));
//
//        List<ReturnTransactionResponse> actualResponse = transactionService.returnTransactionByStatus(TransactionStatus.SUCESSFULL);
//
//        List<ReturnTransactionResponse> expectedResponse = Arrays.asList(
//                ReturnTransactionResponse.From(transaction1),
//                ReturnTransactionResponse.From(transaction2)
//        );
//
//        Assertions.assertIterableEquals(expectedResponse, actualResponse);
//    }
//}
