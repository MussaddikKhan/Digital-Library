package com.myProject.practice.Digital_Library_Project.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private Date transctionDate;
    private Date bookDueDate;
    private  boolean isIssued;
    private Integer fineAmount;
    private  TransactionStatus transactionStatus;
    @ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    @JoinColumn
    private  Book book;

    @ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    @JoinColumn
    private  Card card;
    
}
