package com.myProject.practice.Digital_Library_Project.Entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonIgnoreProperties({"student","transactions"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String name;

    private String language;
    @Enumerated(EnumType.STRING)
    private  Genre genre;
    @CreationTimestamp
    private  Date createdOn;
    @UpdateTimestamp
    private  Date updatedOn;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("books")
    private  Author author;

    @ManyToOne
    @JoinColumn
    private  Student student;

    @OneToMany(mappedBy = "book")
    private List<Transaction>transactions;
}
