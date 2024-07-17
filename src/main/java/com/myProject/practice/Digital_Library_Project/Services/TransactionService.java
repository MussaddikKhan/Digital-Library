package com.myProject.practice.Digital_Library_Project.Services;

import com.myProject.practice.Digital_Library_Project.Dto.*;
import com.myProject.practice.Digital_Library_Project.Entity.*;
import com.myProject.practice.Digital_Library_Project.Exceptions.BookAlreadyIssued;
import com.myProject.practice.Digital_Library_Project.Exceptions.BookLimitExceed;
import com.myProject.practice.Digital_Library_Project.Repository.TransactionRepository;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Value("${books.max_allowed}")
    int maxBookAllowed;

    @Value("${book.fine.per_day}")
    Double finePerDay;

    @Value("${books.max_days_allowed}")
    Integer maxDay;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    BookService bookService;

    /** Get Transaction By ID */
    public  Transaction getTxnById(String ExtTxnId){
        return transactionRepository.findByExternalTxnId(ExtTxnId);
    }


    public String transact(CreateTransactionRequest createTransactionRequest) throws Exception {
        TransactionType transactionType = createTransactionRequest.getTransactionType();
        Student student = studentService.getById(createTransactionRequest.getStudentId());
        Book book = bookService.getBookById(createTransactionRequest.getBookId());

        if (transactionType == TransactionType.ISSUE) {
            return issueBook(student, book, transactionType);
        } else if (transactionType == TransactionType.RETURN) {
            return returnBook(student, book, transactionType);
        } else {
            return "Invalid Transaction Type";
        }
    }

    /** Issue Book  */
    public String issueBook(Student student, Book book, TransactionType transactionType) throws Exception {
        /**
         * 1. create an entry in the txn table with the status as pending : acknowledging that we have received the request to issue
         * 2. whether book is available or not ?
         * 3. whether student has already issued the max no of books allowed
         * 4. update book table set student id = ? i.e making the book unavailable
         * 5. update txn status to success or if anything fails just make the txn status as FAILED
         */
        Transaction t = Transaction.builder()
                .book(book)
                .transactionType(transactionType)
                .transactionStatus(TransactionStatus.PENDING)
                .externalTxnId(UUID.randomUUID().toString())
                .student(student)
                .build();
        this.transactionRepository.save(t);
        try{
            if(book.getStudent() != null){
                throw new BookAlreadyIssued("Book is already Issued by Another Student");
            }
            if(student.getBookList() != null && student.getBookList().size() >= maxBookAllowed)  {
                throw new BookLimitExceed("Student has already issued maximum number of books");
            }
            bookService.updateBookById(book.getId(),student);
            t.setTransactionStatus(TransactionStatus.SUCESSFULL);

        }catch (Exception e){
            t.setTransactionStatus(TransactionStatus.FAILED);
            throw e;
        }
        return transactionRepository.save(t).getExternalTxnId();
    }

    /** Return Book  */
    public String returnBook(Student student, Book book, TransactionType transactionType) {
        /**
         * /**
         *          * 1. create an entry in the txn table with the status as pending : acknowledging that we have received the request to issue
         *          * 2. whether book is available or issue to someone else ? in that case throw an exception
         *          * 3. Check for fine ? when the book was issued and the due date
         *          * 4. update book table set student id = null i.e making the book available
         *          * 5. update txn status to success or if anything fails just make the txn status as FAILED
         *
         */
        Transaction t = Transaction.builder()
                .book(book)
                .transactionType(transactionType)
                .transactionStatus(TransactionStatus.PENDING)
                .externalTxnId(UUID.randomUUID().toString())
                .student(student)
                .build();
        this.transactionRepository.save(t);
        try{
            if(book.getStudent() != null && book.getStudent().getId() != student.getId()){
                throw new Exception("Book is Not Issue to this Student");
            }

            Transaction txn = transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(book, student, TransactionType.ISSUE, TransactionStatus.SUCESSFULL);
            Date issuDate = txn.getCreatedOn();
            Date returnDate = t.getCreatedOn();
            double fineAmount = getFine(issuDate, returnDate);
            bookService.updateBookById(book.getId(), null);
            t.setFine(fineAmount);
            t.setTransactionStatus(TransactionStatus.SUCESSFULL);

        }
        catch (Exception e){
            t.setTransactionStatus(TransactionStatus.FAILED);

        }
        return   transactionRepository.save(t).getExternalTxnId();
    }


    /**  Evaluate Fine */
    public double getFine(Date issuDate, Date returnDate) {
        long timeDiffMillis = Math.abs(issuDate.getTime() - returnDate.getTime());
        long daysPassed = TimeUnit.DAYS.convert(timeDiffMillis, TimeUnit.MILLISECONDS);
        Double fine = 0.0;
        if(daysPassed > maxDay){
            fine = (daysPassed - maxDay) * finePerDay;
        }
        return fine; 
    }

    /** Get Transaction By Status */
    public List<ReturnTransactionResponse> returnTransactionByStatus(TransactionStatus transactionStatus) {
        return transactionRepository.findByTransactionStatus(transactionStatus).stream().map(txn -> ReturnTransactionResponse.From(txn)).collect(Collectors.toList());
    }

    /** Get Transaction By Type*/
    public List<ReturnTransactionResponse> returnTransactionByType(TransactionType transactionType) {
        return transactionRepository.findByTransactionType(transactionType).stream().map(txn -> ReturnTransactionResponse.From(txn)).collect(Collectors.toList());
    }

    /** Get Books By Type */
    public List<ReturnBookResponse> getBookByTxnType(TransactionType transactionType) {
        List<Book> bookList = transactionRepository.findByTransactionType(transactionType).stream().map(txn -> txn.getBook()).collect(Collectors.toList());
        return bookList.stream().map(book -> ReturnBookResponse.from(book)).collect(Collectors.toList());
    }

    /** Get Student who OverDue The Transaction */
    public List<ReturnStudentWithOverDueResponse> getStudentsWithOverDue() {
 /**
  *  1. Find All the Transactions
  *  2. Filter The Transaction with currDate - issueDate is >= due Date if we find
  *  3. Return the List Of Student Fine Response with Student Name , roll Number, bookName , Fine
  *  4. Sent Kafka Msg to that Student (Pending)
  */
        long currentDateMillis = new Date().getTime();

        // Example: Filter transactions based on currentDate and maxDay
        List<Transaction> overDueTransactions = transactionRepository.findByTransactionType(TransactionType.ISSUE).stream()
                .filter(txn -> isOverdue(txn.getCreatedOn() , new Date()))
                .collect(Collectors.toList());

        // Set fine for each overdue transaction  (Lambda
        overDueTransactions.forEach(txn -> {
            double fine = getFine(txn.getCreatedOn(), new Date());
            txn.setFine(fine);
        });

        return overDueTransactions.stream().map(txn -> ReturnStudentWithOverDueResponse.From(txn)).collect(Collectors.toList());
    }
    private boolean isOverdue(Date issueDate, Date returnDate) {
        long timeDiffMillis = returnDate.getTime() - issueDate.getTime();
        long daysPassed = TimeUnit.DAYS.convert(timeDiffMillis, TimeUnit.MILLISECONDS);
        return daysPassed > maxDay;
    }

}
