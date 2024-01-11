package com.myProject.practice.Digital_Library_Project.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Card")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private  Status status;

    @Column(unique = true)
    private  String email;

    private  Date validUpto;

    @CreationTimestamp
    private  Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "cards", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> bookList;

    @OneToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    private  Student student;

    @OneToMany(mappedBy = "card", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;



}

