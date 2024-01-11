package com.myProject.practice.Digital_Library_Project.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String name;

    private Integer number_Of_Pages;

    private String language;
    private  boolean isAvailable;

    @Enumerated(EnumType.STRING)
    private  Genre genre;
    private String ISBN;
    @Temporal(TemporalType.DATE)
    private Date published_Date;
    @CreationTimestamp
    private  Date createdOn;
    @UpdateTimestamp
    private  Date updatedOn;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private  Author author;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn
    private  Card cards;

    @OneToMany(mappedBy = "book", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction>transactions; 
}
