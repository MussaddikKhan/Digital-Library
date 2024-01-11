package com.myProject.practice.Digital_Library_Project.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    private Integer age;
    private String name;
    private String country;
    @Column(unique = true)
    private  String email;
    private String phone_Number;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private  Date updatedOn;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private  Card card;
    
}
