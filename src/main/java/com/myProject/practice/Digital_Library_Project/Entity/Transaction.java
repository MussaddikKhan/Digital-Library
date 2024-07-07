package com.myProject.practice.Digital_Library_Project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String externalTxnId;
    private double fine;

    @Enumerated(value = EnumType.STRING)
    private  TransactionStatus transactionStatus;

    @Enumerated(value = EnumType.STRING)
    private  TransactionType transactionType;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"transactions","student"})
    private  Book book;
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("transactions")
    private  Student student;

    @CreationTimestamp
    private  Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;


    
    
}
