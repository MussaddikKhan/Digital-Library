package com.myProject.practice.Digital_Library_Project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(unique = true)
    private  String username;
    private  String password;
    private String name;
    private  Integer age;
    @Column(unique = true)
    private  String email;
    @Column(unique = true, nullable = false)
    private  String rollNo;
    @Column(unique = true)
    private String phone_Number;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private  Date updatedOn;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties("student")
    private  User user;

    @OneToMany(mappedBy ="student")
    List<Book>bookList;

    @OneToMany(mappedBy = "student")
    List<Transaction>transactions;

}
