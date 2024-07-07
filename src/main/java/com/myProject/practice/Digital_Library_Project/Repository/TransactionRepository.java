package com.myProject.practice.Digital_Library_Project.Repository;

import com.myProject.practice.Digital_Library_Project.Dto.ReturnTransactionResponse;
import com.myProject.practice.Digital_Library_Project.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(Book book, Student student, TransactionType transactionType, TransactionStatus transactionStatus);

    Transaction findByExternalTxnId(String id);

    @Query("Select t from Transaction t where t.transactionStatus = :transactionStatus")
    List<Transaction> findByTransactionStatus(@Param("transactionStatus") TransactionStatus transactionStatus);

    List<Transaction> findByTransactionType(TransactionType transactionType);
}
