package com.myProject.practice.Digital_Library_Project.Dto;

import com.myProject.practice.Digital_Library_Project.Entity.Transaction;
import com.myProject.practice.Digital_Library_Project.Entity.TransactionStatus;
import com.myProject.practice.Digital_Library_Project.Entity.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.criteria.From;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnTransactionResponse {
    private String externalTxnId;
    private double fine;
    private TransactionStatus transactionStatus;
    private TransactionType transactionType;
    private Date createdOn;
    private Date updatedOn;

    public  static ReturnTransactionResponse From(Transaction transaction){
        return   ReturnTransactionResponse.builder()
                .externalTxnId(transaction.getExternalTxnId())
                .fine(transaction.getFine())
                .transactionStatus(transaction.getTransactionStatus())
                .transactionType(transaction.getTransactionType())
                .createdOn(transaction.getCreatedOn())
                .updatedOn(transaction.getUpdatedOn())
                .build();
    }
}
